package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Класс сущность "Продукт" для работы с базой данных.
 */

@Entity
@Table(name = "product")
public class Product {

    @PrePersist
    public void init() {
        if (this.id == null)
            this.id = UUID.randomUUID();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "count")
    private long count;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Comment> comments;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Category> categories;

    public UUID getId() {
        return id;
    }

    public Product setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getCount() {
        return count;
    }

    public Product setCount(long count) {
        this.count = count;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Product setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Product setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }
}
