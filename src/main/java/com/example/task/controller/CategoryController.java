package com.example.task.controller;

import com.example.task.controller.dto.CategoryDto;
import com.example.task.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер категорий продуктов
 *
 * @author Tarkhov Evgeniy
 */

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
     ResponseEntity<Page<CategoryDto>> findAll(@RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "page", required = false) Integer page,
                                               Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) { //TODO добавить ошибки
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
