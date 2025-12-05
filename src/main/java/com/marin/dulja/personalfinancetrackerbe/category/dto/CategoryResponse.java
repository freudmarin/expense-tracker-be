package com.marin.dulja.personalfinancetrackerbe.category.dto;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name
) {}
