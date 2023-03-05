package com.example.task.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Tarkhov Evgeniy
 */
@Data
@Builder
public class ProductWithRatingDto {
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Long count;
    private BigDecimal price;
    private BigDecimal averageRating;
}
