package com.marin.dulja.expensetrackerbe.category.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name
) {}
