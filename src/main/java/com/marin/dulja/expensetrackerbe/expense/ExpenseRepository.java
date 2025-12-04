package com.marin.dulja.expensetrackerbe.expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findAllByClientIdOrderByDateDesc(String clientId);
    List<Expense> findAllByClientIdAndCategoryRef_IdOrderByDateDesc(String clientId, UUID categoryId);
    Optional<Expense> findByIdAndClientId(UUID id, String clientId);
    boolean existsByIdAndClientId(UUID id, String clientId);
    long deleteByIdAndClientId(UUID id, String clientId);
    long deleteByClientIdAndCategoryRef_Id(String clientId, UUID categoryId);
}
