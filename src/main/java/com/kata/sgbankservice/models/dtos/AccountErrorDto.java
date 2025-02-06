package com.kata.sgbankservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountErrorDto implements Serializable {

    private int code;
    private String message;
    private LocalDateTime timestamp;

}
