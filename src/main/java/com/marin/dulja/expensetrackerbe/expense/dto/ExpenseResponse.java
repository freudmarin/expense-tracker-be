package com.marin.dulja.expensetrackerbe.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        String title,
        BigDecimal amount,
        LocalDate date,
        UUID categoryId,
        List<String> tags
) {}
