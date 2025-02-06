package com.kata.sgbankservice.services;


import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.DepositDto;

public interface AccountBankService {

    AccountDto deposit(final DepositDto depositDto);

}
