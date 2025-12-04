package com.marin.dulja.expensetrackerbe.expense;

import com.marin.dulja.expensetrackerbe.expense.dto.ExpenseRequest;
import com.marin.dulja.expensetrackerbe.expense.dto.ExpenseResponse;
import com.marin.dulja.expensetrackerbe.category.Category;
import com.marin.dulja.expensetrackerbe.category.CategoryNotFoundException;
import com.marin.dulja.expensetrackerbe.category.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ExpenseService {

    private final ExpenseRepository repository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    private static ExpenseResponse toResponse(Expense e) {
        List<String> tags = parseTags(e.getTags());
        UUID categoryId = e.getCategoryRef() != null ? e.getCategoryRef().getId() : null;
        return new ExpenseResponse(e.getId(), e.getTitle(), e.getAmount(), e.getDate(), categoryId, tags);
    }

    private static List<String> parseTags(String tagsStr) {
        if (tagsStr == null || tagsStr.isBlank()) return List.of();
        return Arrays.stream(tagsStr.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }

    private static String joinTags(List<String> tags) {
        if (tags == null || tags.isEmpty()) return null;
        List<String> cleaned = new ArrayList<>();
        for (String t : tags) {
            if (t == null) continue;
            String c = t.trim();
            if (!c.isEmpty()) cleaned.add(c);
        }
        if (cleaned.isEmpty()) return null;
        return String.join(",", cleaned);
    }

    public List<ExpenseResponse> list(String clientId) {
        return repository.findAllByClientIdOrderByDateDesc(clientId)
                .stream()
                .map(ExpenseService::toResponse)
                .toList();
    }

    public List<ExpenseResponse> list(String clientId, UUID categoryId) {
        List<Expense> items = (categoryId != null)
                ? repository.findAllByClientIdAndCategoryRef_IdOrderByDateDesc(clientId, categoryId)
                : repository.findAllByClientIdOrderByDateDesc(clientId);
        return items
                .stream()
                .map(ExpenseService::toResponse)
                .toList();
    }

    public ExpenseResponse getOne(UUID id, String clientId) {
        Expense e = repository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
        return toResponse(e);
    }

    @Transactional
    public ExpenseResponse create(ExpenseRequest req, String clientId) {
        Expense e = new Expense();
        e.setClientId(clientId);
        e.setTitle(req.title());
        e.setAmount(req.amount());
        e.setDate(req.date());
        // If categoryId provided, link to Category (must belong to client)
        if (req.categoryId() != null) {
            Category cat = categoryRepository.findByIdAndClientId(req.categoryId(), clientId)
                    .orElseThrow(() -> new CategoryNotFoundException(req.categoryId()));
            e.setCategoryRef(cat);
        } else {
            e.setCategoryRef(null);
        }
        e.setTags(joinTags(req.tags()));
        Expense saved = repository.save(e);
        return toResponse(saved);
    }

    @Transactional
    public ExpenseResponse update(UUID id, ExpenseRequest req, String clientId) {
        Expense e = repository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
        e.setTitle(req.title());
        e.setAmount(req.amount());
        e.setDate(req.date());
        if (req.categoryId() != null) {
            Category cat = categoryRepository.findByIdAndClientId(req.categoryId(), clientId)
                    .orElseThrow(() -> new CategoryNotFoundException(req.categoryId()));
            e.setCategoryRef(cat);
        } else {
            e.setCategoryRef(null);
        }
        e.setTags(joinTags(req.tags()));
        return toResponse(repository.save(e));
    }

    @Transactional
    public void delete(UUID id, String clientId) {
        boolean exists = repository.existsByIdAndClientId(id, clientId);
        if (!exists) {
            throw new ExpenseNotFoundException(id);
        }
        repository.deleteByIdAndClientId(id, clientId);
    }
}
