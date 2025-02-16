package com.kata.sgbankservice.models.entities;

import com.kata.sgbankservice.models.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    private Long id;
    private BigDecimal balance;
    private Date createdAt;
    private AccountStatus status;
    private List<AccountOperationEntity> accountOperations;

}
