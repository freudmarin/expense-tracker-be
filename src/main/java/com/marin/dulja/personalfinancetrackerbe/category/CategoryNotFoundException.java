package com.marin.dulja.personalfinancetrackerbe.category;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Category not found: " + id);
    }
}
