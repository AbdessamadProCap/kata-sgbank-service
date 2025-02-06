package com.kata.sgbankservice.services;


import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.DepositDto;
import com.kata.sgbankservice.models.dtos.WithdrawDto;

public interface AccountBankService {

    AccountDto deposit(final DepositDto depositDto);

    AccountDto withdraw(final WithdrawDto withdrawDto);

}
