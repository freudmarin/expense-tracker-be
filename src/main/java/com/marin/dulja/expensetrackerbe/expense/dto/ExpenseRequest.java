package com.marin.dulja.expensetrackerbe.expense.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ExpenseRequest(
        @NotBlank(message = "title is required")
        @Size(max = 200)
        String title,

        @NotNull(message = "amount is required")
        @Digits(integer = 17, fraction = 2)
        @DecimalMin(value = "0.00", inclusive = false, message = "amount must be greater than 0")
        BigDecimal amount,

        @NotNull(message = "date is required")
        LocalDate date,

        // Optional new structured reference to a Category
        UUID categoryId,

        @Size(max = 50)
        List<@Size(min = 1, max = 50) String> tags
) {}
