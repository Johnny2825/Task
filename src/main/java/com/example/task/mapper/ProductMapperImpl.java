package com.example.task.mapper;

import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

/**
 * @author Tarkhov Evgeniy
 */
@Component
public class ProductMapperImpl implements ProductMapper {


    @Override
    public ProductDto map(ProductEntity productEntity) {
        if (isNull(productEntity)) {
            return null;
        }
        return ProductDto.builder()
                .id(productEntity.getId())
                .categoryId(productEntity.getCategoryId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .count(productEntity.getCount())
                .price(productEntity.getPrice())
                .averageRating(productEntity.getAverageRating())
                .build();
    }

    @Override
    public ProductEntity map(ProductDto productDto) {
        if (isNull(productDto)) {
            return null;
        }
        return ProductEntity.builder()
                .id(productDto.getId())
                .categoryId(productDto.getCategoryId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .count(productDto.getCount())
                .price(productDto.getPrice())
                .averageRating(productDto.getAverageRating())
                .build();
    }
}
