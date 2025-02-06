package com.kata.sgbankservice;

import com.kata.sgbankservice.exceptionshandlers.InvalidAmountException;
import com.kata.sgbankservice.exceptionshandlers.SuspendedAccountException;
import com.kata.sgbankservice.exceptionshandlers.UnknownAccountIdException;
import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.DepositDto;
import com.kata.sgbankservice.services.AccountBankService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DepositTest {

    @Autowired
    AccountBankService accountBankService;

    DepositDto depositDto;

    @BeforeEach
    public void setUp() {
        depositDto = new DepositDto(11L, new BigDecimal(150), "déposer 150 dans mon compte");
    }

    @Test
    public void testDeposit() {
        AccountDto accountDto = accountBankService.deposit(depositDto);
        Assertions.assertEquals(new BigDecimal(10150), accountDto.getBalance());
    }

    @Test
    public void testItShouldThrowInvalidAmountException() {
        depositDto.setAmount(new BigDecimal("-100"));
        assertThrows(InvalidAmountException.class, () -> accountBankService.deposit(depositDto));
    }

    @Test
    public void testItShouldThrowSuspendedAccountException() {
        depositDto.setAccountId(13L);
        assertThrows(SuspendedAccountException.class, () -> accountBankService.deposit(depositDto));
    }

    @Test
    public void testItShouldThrowUnknownAccountIdException() {
        depositDto.setAccountId(null);
        assertThrows(UnknownAccountIdException.class, () -> accountBankService.deposit(depositDto));
    }

    @Test
    public void testAmountZeroItShouldThrowInvalidAmountException() {
        depositDto.setAmount(new BigDecimal("0"));
        assertThrows(InvalidAmountException.class, () -> accountBankService.deposit(depositDto));
    }

}
