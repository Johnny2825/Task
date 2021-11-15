package com.example.task.service;

import com.example.task.dao.CategoryDAO;
import com.example.task.entity.Category;
import org.springframework.stereotype.Service;

/**
 * Класс сервис для работы с категорями.
 */
@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Поиск категории по имени.
     *
     * @param name - имя категории.
     * @returns Category - категория из базы данных.
     */

    public Category findByName(String name) {
        return categoryDAO.findByName(name);
    }
}
