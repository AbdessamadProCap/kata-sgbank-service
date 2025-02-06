package com.kata.sgbankservice.controllers;

import com.kata.sgbankservice.exceptionshandlers.AccountNotFoundException;
import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.DepositDto;
import com.kata.sgbankservice.services.AccountBankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bankaccount")
@Tag(name = "Kata SG Bank Account Operations", description = "Operations related to deposit, withdraw, get account operations history")
public class BankAccountController {

    private final AccountBankService accountBankService;

    public BankAccountController(AccountBankService accountBankService) {
        this.accountBankService = accountBankService;
    }

    @PostMapping("/deposit")
    @Operation(summary = "Deposit into a bank account", description = "Deposits a given amount into a given bank account")
    @Parameter(description = "accountId and amount", required = true)
    public ResponseEntity<AccountDto> deposit(@Valid @RequestBody DepositDto depositDto) throws AccountNotFoundException {
        final AccountDto accountDto = this.accountBankService.deposit(depositDto);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

}
