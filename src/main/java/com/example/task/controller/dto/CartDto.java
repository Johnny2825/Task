package com.example.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tarkhov Evgeniy
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDto {

    private Long cartId;
    private BigDecimal costCart;
    private List<Product> products;

    private static class Product {
        private String name;
        private BigDecimal price;
        private Long count;
    }
}
