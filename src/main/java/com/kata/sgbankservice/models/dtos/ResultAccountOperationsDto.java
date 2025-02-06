package com.kata.sgbankservice.models.dtos;

import com.kata.sgbankservice.models.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultAccountOperationsDto {

    private Long accountId;
    private BigDecimal balance;
    private AccountStatus status;
    private List<AccountOperationDto> accountOperations;

}
