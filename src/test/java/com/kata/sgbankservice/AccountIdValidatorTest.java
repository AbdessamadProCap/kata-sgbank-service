package com.kata.sgbankservice;


import com.kata.sgbankservice.models.validators.AccountIdValidator;
import com.kata.sgbankservice.models.validators.ValidAccountId;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AccountIdValidatorTest {

    private AccountIdValidator accountIdValidator = new AccountIdValidator();

    @Mock
    ConstraintValidatorContext context;

    @Test
    public void shouldReturnTrueWhenAccountIdIsValid() {
        ValidAccountId validator = createAnnotation(2, 20);
        accountIdValidator.initialize(validator);

        assertTrue(accountIdValidator.isValid(1111111122222L, context));
    }

    @ParameterizedTest
    @ValueSource(longs = {111L, 222L})
    void shouldReturnTrueWhenAccountIdsAreValid(Long accountId) {
        ValidAccountId validator = createAnnotation(2, 20);
        accountIdValidator.initialize(validator);
        assertTrue(accountIdValidator.isValid(accountId, context));
    }

    private ValidAccountId createAnnotation(int min, int max) {
        return new ValidAccountId() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return ValidAccountId.class;
            }

            @Override
            public String message() {
                return "{jakarta.validation.constraints.ValidAccountId.message}";
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public int min() {
                return min;
            }

            @Override
            public int max() {
                return max;
            }
        };
    }

}
