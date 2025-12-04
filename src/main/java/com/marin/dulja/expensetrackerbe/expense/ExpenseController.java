package com.marin.dulja.expensetrackerbe.expense;

import com.marin.dulja.expensetrackerbe.expense.dto.ExpenseRequest;
import com.marin.dulja.expensetrackerbe.expense.dto.ExpenseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExpenseResponse> list(@RequestHeader("X-Client-Id") String clientId,
                                      @RequestParam(value = "categoryId", required = false) UUID categoryId) {
        return service.list(clientId, categoryId);
    }

    @GetMapping("/{id}")
    public ExpenseResponse getOne(@PathVariable UUID id, @RequestHeader("X-Client-Id") String clientId) {
        return service.getOne(id, clientId);
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(@Valid @RequestBody ExpenseRequest req,
                                                  @RequestHeader("X-Client-Id") String clientId) {
        ExpenseResponse saved = service.create(req, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ExpenseResponse update(@PathVariable UUID id,
                                  @Valid @RequestBody ExpenseRequest req,
                                  @RequestHeader("X-Client-Id") String clientId) {
        return service.update(id, req, clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @RequestHeader("X-Client-Id") String clientId) {
        service.delete(id, clientId);
        return ResponseEntity.noContent().build();
    }
}
