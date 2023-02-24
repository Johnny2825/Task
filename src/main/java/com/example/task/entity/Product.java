package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Класс сущность "Продукт" для работы с базой данных.
 */

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "count")
    private long count;

    @Column(name = "price")
    private BigDecimal price;

//    @OneToMany(mappedBy = "product")
//    @JsonIgnore
//    private List<Comment> comments;

//    @ManyToMany(mappedBy = "products")
//    @JsonIgnore
//    private List<Category> categories;
}
