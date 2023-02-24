package com.example.task.service.category;

import com.example.task.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findByName(String name);

    Category findById(String id);
}
