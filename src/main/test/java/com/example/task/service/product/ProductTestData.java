package com.example.task.service.product;

import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;
import liquibase.pro.packaged.N;

import java.math.BigDecimal;

/**
 * @author Tarkhov Evgeniy
 */
public class ProductTestData {
    private static final Long ID = 1L;
    private static final Long CATEGORY_ID = 11L;
    private static final String NAME = "Milk";
    private static final String DESCRIPTION = "Just milk";
    private static final Long COUNT = 43L;
    private static final BigDecimal PRICE = BigDecimal.valueOf(43.65);
    private static final BigDecimal AVERAGE_RATING = BigDecimal.valueOf(4.2);


    public ProductEntity getProductEntity1() {
        return ProductEntity.builder()
                .id(ID)
                .categoryId(CATEGORY_ID)
                .name(NAME)
                .description(DESCRIPTION)
                .count(COUNT)
                .price(PRICE)
                .averageRating(AVERAGE_RATING)
                .build();
    }

    public ProductDto getProductDto1() {
        return ProductDto.builder()
                .id(ID)
                .categoryId(CATEGORY_ID)
                .name(NAME)
                .description(DESCRIPTION)
                .count(COUNT)
                .price(PRICE)
                .averageRating(AVERAGE_RATING)
                .build();
    }

    public ProductEntity getProductEntity2() {
        return ProductEntity.builder()
                .id(ID)
                .categoryId(4L)
                .name("Potato")
                .description("Just potato")
                .count(666L)
                .price(BigDecimal.valueOf(12.5))
                .averageRating(AVERAGE_RATING)
                .build();
    }

    public ProductDto getProductDto2() {
        return ProductDto.builder()
                .id(ID)
                .categoryId(4L)
                .name("Potato")
                .description("Just potato")
                .count(666L)
                .price(BigDecimal.valueOf(12.5))
                .averageRating(AVERAGE_RATING)
                .build();
    }

}
