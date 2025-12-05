package com.marin.dulja.personalfinancetrackerbe.transaction;

import com.marin.dulja.personalfinancetrackerbe.category.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions", indexes = {
        @Index(name = "idx_transactions_client_id", columnList = "client_id")
})
public class Transaction {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false, length = 100)
    private String clientId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;

    // Comma separated list of tags for simple storage
    @Column(length = 500)
    private String tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryRef;

    // New: type of transaction - "income" or "expense"
    @Column(nullable = false, length = 10)
    private String type;

    public Transaction() {
    }

    public Transaction(UUID id, String clientId, String title, BigDecimal amount, LocalDate date) {
        this.id = id;
        this.clientId = clientId;
        this.title = title;
        this.amount = amount;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Category getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(Category categoryRef) {
        this.categoryRef = categoryRef;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
