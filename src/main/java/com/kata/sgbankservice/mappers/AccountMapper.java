package com.kata.sgbankservice.mappers;


import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.entities.AccountEntity;

public interface AccountMapper {

    AccountDto fromBankAccountEntityToDto(final AccountEntity accountEntity);

    AccountEntity fromBankAccountDtoToEntity(final AccountDto accountDto);

}
