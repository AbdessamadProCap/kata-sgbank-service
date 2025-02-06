package com.kata.sgbankservice;


import com.kata.sgbankservice.exceptionshandlers.SuspendedAccountException;
import com.kata.sgbankservice.models.dtos.AccountOperationDto;
import com.kata.sgbankservice.models.dtos.ResultAccountOperationsDto;
import com.kata.sgbankservice.services.AccountBankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountOperationsHistoryApplicationTest {

    @Autowired
    private AccountBankService accountBankService;

    @Test
    public void testSuspendedItShouldThrowSuspendedAccountException() {
        assertThrows(SuspendedAccountException.class,
                () -> accountBankService.accountOperationsHistory(13L));
    }

    @Test
    public void testlengthOfAccountOperationsEmptyByDefault() {
        ResultAccountOperationsDto resultAccountOperationsDto = accountBankService.accountOperationsHistory(11L);
        final List<AccountOperationDto> accountOperationDtos = resultAccountOperationsDto.getAccountOperations();
        assertTrue(accountOperationDtos.isEmpty());
    }


}
