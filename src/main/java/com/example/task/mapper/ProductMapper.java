package com.example.task.mapper;

import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;

/**
 * @author Tarkhov Evgeniy
 */
public interface ProductMapper {

    ProductDto map(ProductEntity productEntity);

    ProductEntity map(ProductDto productDto);
}
