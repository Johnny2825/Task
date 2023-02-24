package com.example.task.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

/**
 * Класс сущность "Комментарий" для работы с базой данных.
 */

@Entity
@Table(name = "comment")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
}
