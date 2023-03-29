package com.example.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Exclude
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private Long count;
    private BigDecimal price;
    private BigDecimal averageRating;
}
