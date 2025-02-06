package com.kata.sgbankservice.models.dtos;


import com.kata.sgbankservice.models.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    private Long id;
    private BigDecimal balance;
    private Date createdAt;
    private AccountStatus status;

}
