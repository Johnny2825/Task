package com.example.task.service.category;

import com.example.task.controller.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryInternalService categoryInternalService;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryInternalService.findAll(pageable);
    }

    @Override
    public Optional<CategoryDto> findById(Long categoryId) {
        return categoryInternalService.findById(categoryId);
    }
}
