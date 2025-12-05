package com.marin.dulja.personalfinancetrackerbe.transaction.dto;

import com.marin.dulja.personalfinancetrackerbe.category.dto.CategoryResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        String title,
        BigDecimal amount,
        LocalDate date,
        String type,
        CategoryResponse category,
        List<String> tags
) {}
