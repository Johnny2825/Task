package com.example.task.service.category;

import com.example.task.entity.Category;
import com.example.task.repository.CategoryRepository;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.task.utils.Utils.parseLongSafety;
import static java.util.Objects.isNull;

/**
 * Класс сервис для работы с категорями.
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Поиск категории по имени.
     *
     * @returns Category - категория из базы данных.
     */

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        if (isNull(name) || name.isEmpty()) {
            return null;
        }
        return categoryRepository.findByName(name);
    }

    @Override
    public Category findById(String idStr) {
        if (isNull(idStr)) {
            return null;
        }
        Long id = parseLongSafety(idStr);
        if (isNull(id)) {
            return null;
        }
        return categoryRepository.findById(id).orElse(null);
    }



}
