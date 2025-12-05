package com.marin.dulja.personalfinancetrackerbe.transaction.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TransactionRequest(
        @NotBlank(message = "title is required")
        @Size(max = 200)
        String title,

        @NotNull(message = "amount is required")
        @Digits(integer = 17, fraction = 2)
        @DecimalMin(value = "0.00", inclusive = false, message = "amount must be greater than 0")
        BigDecimal amount,

        @NotNull(message = "date is required")
        LocalDate date,

        // New required type field: "income" or "expense"
        @NotBlank(message = "type is required")
        @Pattern(regexp = "^(income|expense)$", message = "type must be either 'income' or 'expense'")
        String type,

        // Optional new structured reference to a Category
        UUID categoryId,

        @Size(max = 50)
        List<@Size(min = 1, max = 50) String> tags
) {}
