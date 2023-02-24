package com.example.task.controller;

import com.example.task.entity.Category;

import com.example.task.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер категорий проуктов
 *
 * @author Tarkhov Evgeniy
 */

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    Category findById(@PathVariable("id") String id) {
        return categoryService.findById(id);
    }

    @GetMapping("/category/name")
    Category findByName(@RequestParam("name") String name) {
        return categoryService.findByName(name);
    }
}
