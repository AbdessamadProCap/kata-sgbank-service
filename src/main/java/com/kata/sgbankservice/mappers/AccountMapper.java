package com.kata.sgbankservice.mappers;


import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.dtos.AccountOperationDto;
import com.kata.sgbankservice.models.dtos.ResultAccountOperationsDto;
import com.kata.sgbankservice.models.entities.AccountEntity;
import com.kata.sgbankservice.models.entities.AccountOperationEntity;

import java.util.List;

public interface AccountMapper {

    AccountDto fromBankAccountEntityToDto(final AccountEntity accountEntity);

    AccountEntity fromBankAccountDtoToEntity(final AccountDto accountDto);

    AccountOperationDto fromAccountOperationEntityToDto(final AccountOperationEntity accountOperationEntity);

    AccountOperationEntity fromAccountOperationDtoToEntity(final AccountOperationDto accountOperationDto);

    ResultAccountOperationsDto resultAccountOperationsDtoMapper(final AccountEntity bankAccount, final List<AccountOperationDto> accountOperationDtosList);

}
