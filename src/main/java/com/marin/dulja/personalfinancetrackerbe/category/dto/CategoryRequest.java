package com.marin.dulja.personalfinancetrackerbe.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "name is required")
        @Size(max = 100)
        String name
) {}
