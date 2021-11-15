package com.example.task.dto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Класс шаблон для отправки JSON.
 */

public class ProductWithRating {
    private UUID id;
    private String name;
    private String description;
    private long count;
    private BigDecimal price;
    private BigDecimal averageRating;

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public ProductWithRating setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ProductWithRating setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductWithRating setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductWithRating setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getCount() {
        return count;
    }

    public ProductWithRating setCount(long count) {
        this.count = count;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductWithRating setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
