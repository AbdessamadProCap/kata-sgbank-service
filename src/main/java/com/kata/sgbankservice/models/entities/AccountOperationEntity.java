package com.kata.sgbankservice.models.entities;


import com.kata.sgbankservice.models.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperationEntity {

    private Long id;
    private Date operationDate;
    private BigDecimal amount;
    private BigDecimal balance;
    private OperationType type;
    private AccountEntity bankAccount;
    private String description;

}
