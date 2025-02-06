package com.kata.sgbankservice.models.dtos;


import com.kata.sgbankservice.models.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperationDto {
    private Long id;
    private Date operationDate;
    private BigDecimal amount;
    private OperationType type;
    private String description;

}
