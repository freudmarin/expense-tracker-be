package com.marin.dulja.personalfinancetrackerbe.category;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categories", indexes = {
        @Index(name = "idx_categories_client_id", columnList = "client_id"),
        @Index(name = "uk_categories_client_name", columnList = "client_id,name", unique = true)
})
public class Category {

    @Id
    @GeneratedValue
    @org.hibernate.annotations.UuidGenerator
    @Column(nullable = false)
    private UUID id;

    @Column(name = "client_id", nullable = false, length = 100)
    private String clientId;

    @Column(nullable = false, length = 100)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
