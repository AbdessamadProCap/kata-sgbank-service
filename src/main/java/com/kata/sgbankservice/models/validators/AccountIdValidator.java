package com.kata.sgbankservice.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

/**
 * This is done on purpose to share with you my way of customizing specific spring validators
 */
public class AccountIdValidator implements ConstraintValidator<ValidAccountId, Long> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidAccountId constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if (isNull(value)) return false;
        return value.toString().length() >= min && value.toString().length() <= max;
    }
}
