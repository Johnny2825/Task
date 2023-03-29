package com.example.task.service.category;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.entity.CategoryEntity;

/**
 * @author Tarkhov Evgeniy
 */
public class CategoryTestData {


    public CategoryDto getCategoryDto1() {
        return new CategoryDto(1L, "Молочная продукция");
    }

    public CategoryDto getCategoryDto2() {
        return new CategoryDto(2L, "Мясная продукция");
    }

    public CategoryEntity getCategoryEntity1() {
        return new CategoryEntity(1L, "Молочная продукция");
    }

    public CategoryEntity getCategoryEntity2() {
        return new CategoryEntity(2L, "Мясная продукция");
    }
}
