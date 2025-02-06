package com.kata.sgbankservice.mappers;

import com.kata.sgbankservice.exceptionshandlers.AccountIsNullException;
import com.kata.sgbankservice.models.dtos.AccountDto;
import com.kata.sgbankservice.models.entities.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDto fromBankAccountEntityToDto(final AccountEntity accountEntity) {
        if (accountEntity == null) {
            log.error("Account entity is unknown");
            throw new AccountIsNullException("Account entity is unknown");
        }
        final AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(accountEntity, accountDto);
        return accountDto;
    }

    @Override
    public AccountEntity fromBankAccountDtoToEntity(final AccountDto accountDto) {
        if (accountDto == null) {
            log.error("Account dto is unknown");
            throw new AccountIsNullException("Account dto is unknown");
        }
        final AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(accountDto, accountEntity);
        return accountEntity;
    }

}