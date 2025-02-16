package com.kata.sgbankservice.services.impl;

import com.kata.sgbankservice.exceptionshandlers.*;
import com.kata.sgbankservice.mappers.AccountMapper;
import com.kata.sgbankservice.models.dtos.*;
import com.kata.sgbankservice.models.entities.AccountEntity;
import com.kata.sgbankservice.models.entities.AccountOperationEntity;
import com.kata.sgbankservice.models.enums.AccountStatus;
import com.kata.sgbankservice.models.enums.OperationType;
import com.kata.sgbankservice.services.AccountBankService;
import com.kata.sgbankservice.utils.CalculUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        account1.setAccountOperations(new ArrayList<>());

        final AccountEntity account2 = new AccountEntity();
        account2.setId(12L);
        account2.setBalance(new BigDecimal(15000));
        account2.setCreatedAt(new Date());
        account2.setStatus(AccountStatus.ACTIVATED);
        account2.setAccountOperations(new ArrayList<>());

        final AccountEntity account3 = new AccountEntity();
        account3.setId(13L);
        account3.setBalance(new BigDecimal(4000));
        account3.setCreatedAt(new Date());
        account3.setStatus(AccountStatus.SUSPENDED);
        account3.setAccountOperations(new ArrayList<>());

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

        final AccountOperationEntity accountOperation = buildAccountOperationEntity(OperationType.DEPOSIT, amount, description, bankAccount);
        bankAccount.getAccountOperations().add(accountOperation);

        log.info("The account with id : {} has been successfully credited", +accountId);
        return accountMapper.fromBankAccountEntityToDto(bankAccount);
    }

    @Override
    public AccountDto withdraw(final WithdrawDto withdrawDto) {

        log.info("Starting new withdraw operation !");

        final Long accountId = withdrawDto.getAccountId();
        final BigDecimal amount = withdrawDto.getAmount();
        final String description = withdrawDto.getDescription();

        validateCommonInputs(accountId, amount);

        final AccountEntity bankAccount = getAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("BankAccount not found"));

        verifyIfAccountIsSuspended(bankAccount);

        if (bankAccount.getBalance().compareTo(amount) < 0)
            throw new BalanceNotSufficientException("Balance not sufficient");

        final BigDecimal newBalanceValue = bankAccount.getBalance().subtract(amount);
        bankAccount.setBalance(newBalanceValue);

        final AccountOperationEntity accountOperation = buildAccountOperationEntity(OperationType.WITHDRAW, amount, description, bankAccount);
        bankAccount.getAccountOperations().add(accountOperation);

        log.info("The account with id : {} has been successfully debited", +accountId);

        return accountMapper.fromBankAccountEntityToDto(bankAccount);
    }

    @Override
    public ResultAccountOperationsDto accountOperationsHistory(final Long accountId) {

        log.info("Start getting account operations history!");

        if (accountId == null) throw new UnknownAccountIdException("The accountId should not be unknown");

        final AccountEntity bankAccount = getAccountById(accountId).orElseThrow(() -> new AccountNotFoundException("BankAccount not found"));

        final List<AccountOperationEntity> accountOperations = getAccountOperationsHistoryBId(bankAccount);

        log.info("Finish getting account operations !");

        final List<AccountOperationDto> accountOperationDtosList = accountOperations.stream().map(accountMapper::fromAccountOperationEntityToDto).collect(Collectors.toList());
        return accountMapper.resultAccountOperationsDtoMapper(bankAccount, accountOperationDtosList);
    }

    private static AccountOperationEntity buildAccountOperationEntity(final OperationType operationType,
                                                                      final BigDecimal amount,
                                                                      final String description,
                                                                      final AccountEntity bankAccount) {
        final AccountOperationEntity accountOperation = new AccountOperationEntity();
        accountOperation.setId(CalculUtils.generateIdRandom());
        accountOperation.setType(operationType);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        return accountOperation;
    }

    private List<AccountOperationEntity> getAccountOperationsHistoryBId(final AccountEntity bankAccount) {

        verifyIfAccountIsSuspended(bankAccount);

        final List<AccountOperationEntity> accountOperations = bankAccount.getAccountOperations();

        return accountOperations != null && !accountOperations.isEmpty() ? accountOperations : new ArrayList<>();
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
