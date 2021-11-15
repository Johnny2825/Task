package com.example.task.dto;

import com.example.task.entity.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    public ProductWithRating(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.count = product.getCount();
        this.price = product.getPrice();
        this.averageRating = new BigDecimal(BigInteger.ZERO);
    }

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
