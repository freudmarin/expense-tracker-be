package com.marin.dulja.personalfinancetrackerbe.transaction;

import com.marin.dulja.personalfinancetrackerbe.transaction.dto.TransactionRequest;
import com.marin.dulja.personalfinancetrackerbe.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<TransactionResponse> list(@RequestHeader("X-Client-Id") String clientId,
                                      @RequestParam(value = "categoryId", required = false) UUID categoryId,
                                      @RequestParam(value = "type", required = false) String type) {
        return service.list(clientId, type, categoryId);
    }

    @GetMapping("/{id}")
    public TransactionResponse getOne(@PathVariable UUID id, @RequestHeader("X-Client-Id") String clientId) {
        return service.getOne(id, clientId);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest req,
                                                  @RequestHeader("X-Client-Id") String clientId) {
        TransactionResponse saved = service.create(req, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public TransactionResponse update(@PathVariable UUID id,
                                  @Valid @RequestBody TransactionRequest req,
                                  @RequestHeader("X-Client-Id") String clientId) {
        return service.update(id, req, clientId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, @RequestHeader("X-Client-Id") String clientId) {
        service.delete(id, clientId);
        return ResponseEntity.noContent().build();
    }
}
