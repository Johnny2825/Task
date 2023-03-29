package com.example.task.service.category;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.CategoryEntity;
import com.example.task.entity.ProductEntity;
import com.example.task.mapper.CategoryMapper;
import com.example.task.repository.CategoryRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Tarkhov Evgeniy
 */
@ExtendWith(MockitoExtension.class)
class CategoryInternalServiceImplTest {
    CategoryTestData categoryTestData = new CategoryTestData();
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    private CategoryInternalService service;

    @BeforeEach
    void setUp() {
        service = new CategoryInternalServiceImpl(categoryRepository,
                categoryMapper);
    }

    @Test
    void findAll() {
        CategoryDto categoryDto1 = categoryTestData.getCategoryDto1();
        CategoryDto categoryDto2 = categoryTestData.getCategoryDto2();
        List<CategoryDto> expected = List.of(categoryDto1, categoryDto2);

        CategoryEntity categoryEntity1 = categoryTestData.getCategoryEntity1();
        CategoryEntity categoryEntity2 = categoryTestData.getCategoryEntity2();
        List<CategoryEntity> categoryEntityList = List.of(categoryEntity1, categoryEntity2);

        when(categoryRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(categoryEntityList));
        when(categoryMapper.map(any(CategoryEntity.class))).thenAnswer(invocationOnMock -> {
            CategoryEntity entity = invocationOnMock.getArgument(0);
            return CategoryDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build();
        });

        Page<CategoryDto> result = service.findAll(Pageable.unpaged());
        assertTrue(CollectionUtils.isEqualCollection(expected, result.getContent()));
    }

    @Test
    void findById() {
        Long categoryId = 1L;
        CategoryEntity categoryEntity = categoryTestData.getCategoryEntity1();
        CategoryDto expected = categoryTestData.getCategoryDto1();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.ofNullable(categoryEntity));
        when(categoryMapper.map(categoryEntity)).thenReturn(expected);

        Optional<CategoryDto> result = service.findById(categoryId);
        assertThat(result)
                .matches(Optional::isPresent)
                .matches(v -> Objects.equals(expected, v.get()));
    }
}