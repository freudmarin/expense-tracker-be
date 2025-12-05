package com.marin.dulja.personalfinancetrackerbe.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByClientIdOrderByDateDesc(String clientId);
    List<Transaction> findAllByClientIdAndCategoryRef_IdOrderByDateDesc(String clientId, UUID categoryId);
    Optional<Transaction> findByIdAndClientId(UUID id, String clientId);
    boolean existsByIdAndClientId(UUID id, String clientId);
    long deleteByIdAndClientId(UUID id, String clientId);
    long deleteByClientIdAndCategoryRef_Id(String clientId, UUID categoryId);

    // New: filter by type
    List<Transaction> findAllByClientIdAndTypeOrderByDateDesc(String clientId, String type);
    List<Transaction> findAllByClientIdAndTypeAndCategoryRef_IdOrderByDateDesc(String clientId, String type, UUID categoryId);
}
