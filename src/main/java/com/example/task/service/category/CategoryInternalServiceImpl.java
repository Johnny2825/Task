package com.example.task.service.category;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.entity.CategoryEntity;
import com.example.task.mapper.CategoryMapper;
import com.example.task.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с категорями продуктов
 *
 * @author Tarkhov Evgeniy
 */
@Service
@RequiredArgsConstructor
public class CategoryInternalServiceImpl implements CategoryInternalService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        List<CategoryDto> categoryDtoList = categoryRepository.findAll(pageable)//TODO посмотреть может ли вернуться лишние
                .stream()
                .map(categoryMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(categoryDtoList, pageable, categoryDtoList.size());
    }

    @Override
    public Optional<CategoryDto> findById(Long categoryId) {
         Optional<CategoryEntity> categoryEntityOptional = categoryRepository.findById(categoryId);
         return categoryEntityOptional.map(categoryMapper::map);
    }



}
