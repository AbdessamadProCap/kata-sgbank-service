package com.kata.sgbankservice.services.impl;

import com.kata.sgbankservice.exceptionshandlers.AccountNotFoundException;
import com.kata.sgbankservice.exceptionshandlers.InvalidAmountException;
import com.kata.sgbankservice.exceptionshandlers.SuspendedAccountException;
import com.kata.sgbankservice.exceptionshandlers.UnknownAccountIdException;
import com.kata.sgbankservice.mappers.AccountMapper;
import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.DepositDto;
import com.kata.sgbankservice.models.entities.AccountEntity;
import com.kata.sgbankservice.models.enums.AccountStatus;
import com.kata.sgbankservice.services.AccountBankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class AccountBankServiceImpl implements AccountBankService {

    private static List<com.kata.sgbankservice.models.entities.AccountEntity> accountsList = new ArrayList<>();

    static {
        // I simulate the database as a static list
        final AccountEntity account1 = new AccountEntity();
        account1.setId(11L);
        account1.setBalance(new BigDecimal(10000));
        account1.setCreatedAt(new Date());
        account1.setStatus(AccountStatus.ACTIVATED);

        final AccountEntity account2 = new AccountEntity();
        account2.setId(12L);
        account2.setBalance(new BigDecimal(15000));
        account2.setCreatedAt(new Date());
        account2.setStatus(AccountStatus.ACTIVATED);

        final AccountEntity account3 = new AccountEntity();
        account3.setId(13L);
        account3.setBalance(new BigDecimal(4000));
        account3.setCreatedAt(new Date());
        account3.setStatus(AccountStatus.SUSPENDED);

        AccountBankServiceImpl.accountsList = List.of(account1, account2, account3);
    }

    private final AccountMapper accountMapper;

    public AccountBankServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto deposit(final DepositDto depositDto) {

        log.info("Starting new deposit operation !");

        final Long accountId = depositDto.getAccountId();
        final BigDecimal amount = depositDto.getAmount();
        final String description = depositDto.getDescription();

        validateCommonInputs(accountId, amount);

        final AccountEntity bankAccount = getAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("BankAccount not found"));

        verifyIfAccountIsSuspended(bankAccount);

        final BigDecimal amoutDecimal = new BigDecimal(String.valueOf(amount));
        final BigDecimal balanceDecimal = bankAccount.getBalance();

        final BigDecimal newBalanceValue = balanceDecimal.add(amoutDecimal);
        bankAccount.setBalance(newBalanceValue);

        log.info("The account with id : {} has been successfully credited", +accountId);
        return accountMapper.fromBankAccountEntityToDto(bankAccount);
    }

    private static void verifyIfAccountIsSuspended(AccountEntity bankAccount) {
        if (AccountStatus.SUSPENDED.equals(bankAccount.getStatus()))
            throw new SuspendedAccountException("Your bank account is suspended no operations will be allowed");
    }

    private void validateCommonInputs(final Long accountId, final BigDecimal amount) {

        if (accountId == null) throw new UnknownAccountIdException("The accountId should not be unknown");

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("The amount must be greater than zero");

    }

    private static Optional<AccountEntity> getAccountById(final Long accountId) {
        return accountsList.stream().filter(account -> Objects.equals(accountId, account.getId())).findFirst();
    }

}
