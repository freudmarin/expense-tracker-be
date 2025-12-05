package com.marin.dulja.personalfinancetrackerbe.category;

import com.marin.dulja.personalfinancetrackerbe.category.dto.CategoryRequest;
import com.marin.dulja.personalfinancetrackerbe.category.dto.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryResponse> list(@RequestHeader("X-Client-Id") String clientId) {
        return service.list(clientId);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest req,
                                                   @RequestHeader("X-Client-Id") String clientId) {
        CategoryResponse saved = service.create(req, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable UUID id,
                                   @Valid @RequestBody CategoryRequest req,
                                   @RequestHeader("X-Client-Id") String clientId) {
        return service.update(id, req, clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id,
                                       @RequestHeader("X-Client-Id") String clientId) {
        service.delete(id, clientId);
        return ResponseEntity.noContent().build();
    }
}
