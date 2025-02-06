package com.kata.sgbankservice.models.dtos;


import com.kata.sgbankservice.models.validators.ValidAccountId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawDto implements Serializable {

    @NotNull(message = "The accountIs is mandatory")
    @ValidAccountId(message = "accountId must contain between 2 and 20 characters")
    private Long accountId;
    @NotNull(message = "The amount is mandatory")
    private BigDecimal amount;
    private String description;

}
