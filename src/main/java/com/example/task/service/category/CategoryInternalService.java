package com.example.task.service.category;

import com.example.task.controller.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Сервис для работы с категорями продуктов
 *
 * @author Tarkhov Evgeniy
 */
public interface CategoryInternalService {

    Page<CategoryDto> findAll(Pageable pageable);

    Optional<CategoryDto> findById(Long categoryId);

}
