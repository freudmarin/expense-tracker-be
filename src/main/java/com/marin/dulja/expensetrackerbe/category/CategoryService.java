package com.marin.dulja.expensetrackerbe.category;

import com.marin.dulja.expensetrackerbe.category.dto.CategoryRequest;
import com.marin.dulja.expensetrackerbe.category.dto.CategoryResponse;
import com.marin.dulja.expensetrackerbe.expense.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository repository;
    private final ExpenseRepository expenseRepository;

    public CategoryService(CategoryRepository repository, ExpenseRepository expenseRepository) {
        this.repository = repository;
        this.expenseRepository = expenseRepository;
    }

    public List<CategoryResponse> list(String clientId) {
        return repository.findAllByClientIdOrderByNameAsc(clientId)
                .stream()
                .map(c -> new CategoryResponse(c.getId(), c.getName()))
                .toList();
    }

    @Transactional
    public CategoryResponse create(CategoryRequest req, String clientId) {
        Category c = new Category();
        c.setClientId(clientId);
        c.setName(req.name());
        Category saved = repository.save(c);
        return new CategoryResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public CategoryResponse update(UUID id, CategoryRequest req, String clientId) {
        Category c = repository.findByIdAndClientId(id, clientId)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        c.setName(req.name());
        Category saved = repository.save(c);
        return new CategoryResponse(saved.getId(), saved.getName());
    }

    @Transactional
    public void delete(UUID id, String clientId) {
        boolean exists = repository.existsByIdAndClientId(id, clientId);
        if (!exists) throw new CategoryNotFoundException(id);
        // First delete all expenses that belong to this client and reference the category
        expenseRepository.deleteByClientIdAndCategoryRef_Id(clientId, id);
        // Then delete the category itself
        repository.deleteByIdAndClientId(id, clientId);
    }
}
