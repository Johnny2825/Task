package com.example.task.mapper;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

/**
 * @author Tarkhov Evgeniy
 */
@Component
public class CategoryMapperImpl implements CategoryMapper {


    @Override
    public CategoryDto map(CategoryEntity categoryEntity) {
        if (isNull(categoryEntity)) {
            return null;
        }
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }

    @Override
    public CategoryEntity map(CategoryDto categoryDto) {
        if (isNull(categoryDto)) {
            return null;
        }
        return CategoryEntity.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }
}
