package com.kata.sgbankservice.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    private Long id;
    private BigDecimal balance;
    private Date createdAt;
    private com.kata.sgbankservice.models.enums.AccountStatus status;

}
