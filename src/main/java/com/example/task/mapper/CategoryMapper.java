package com.example.task.mapper;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.entity.CategoryEntity;

/**
 * @author Tarkhov Evgeniy
 */
public interface CategoryMapper {

    CategoryDto map(CategoryEntity categoryEntity);

    CategoryEntity map(CategoryDto categoryDto);
}
