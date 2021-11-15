package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

/**
 * Класс сущность "Комментарий" для работы с базой данных.
 */

@Entity
@Table(name = "comment")
public class Comment {

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

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "rating")
    private byte rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    @JsonIgnore
    private Product product;

    @Override
    public String toString() {
        return String.format("%s %s: %s", name, rating, content);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
