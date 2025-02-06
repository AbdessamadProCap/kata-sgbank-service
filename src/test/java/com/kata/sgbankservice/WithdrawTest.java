package com.kata.sgbankservice;


import com.kata.sgbankservice.exceptionshandlers.BalanceNotSufficientException;
import com.kata.sgbankservice.exceptionshandlers.InvalidAmountException;
import com.kata.sgbankservice.exceptionshandlers.SuspendedAccountException;
import com.kata.sgbankservice.exceptionshandlers.UnknownAccountIdException;
import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.WithdrawDto;
import com.kata.sgbankservice.services.AccountBankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class WithdrawTest {

    @Autowired
    private AccountBankService accountBankService;

    private WithdrawDto withdrawDto;

    @BeforeEach
    public void setUp() {
        withdrawDto = new WithdrawDto(11L, new BigDecimal("120000"), "retirer 12000 de mon compte");
    }

    @Test
    public void testDeposit() {
        final WithdrawDto dto = new WithdrawDto(12L, new BigDecimal("5000"), "retirer 5000 de mon compte");

        AccountDto accountDto = accountBankService.withdraw(dto);
        Assertions.assertEquals(new BigDecimal(10000), accountDto.getBalance());
    }

    @Test
    public void testItShouldThrowBalanceNotSufficientException() {
        assertThrows(BalanceNotSufficientException.class,
                () -> accountBankService.withdraw(withdrawDto));
    }

    @Test
    public void testItShouldThrowInvalidAmountException() {
        withdrawDto.setAmount(new BigDecimal("-400"));
        assertThrows(InvalidAmountException.class,
                () -> accountBankService.withdraw(withdrawDto));
    }

    @Test
    public void testItShouldThrowSuspendedAccountException() {
        withdrawDto.setAccountId(13L);
        assertThrows(SuspendedAccountException.class,
                () -> accountBankService.withdraw(withdrawDto));
    }

    @Test
    public void testItShouldThrowUnknownAccountIdException() {
        withdrawDto.setAccountId(null);
        assertThrows(UnknownAccountIdException.class,
                () -> accountBankService.withdraw(withdrawDto));
    }

    @Test
    public void testAmountZeroItShouldThrowInvalidAmountException() {
        withdrawDto.setAmount(new BigDecimal("0"));
        assertThrows(InvalidAmountException.class,
                () -> accountBankService.withdraw(withdrawDto));
    }

}
