package com.example.task.service.product;

import com.example.task.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> test();

    List<Product> findAll();

    List<Product> findAllMoreThan(String value);

    Product findById(String id);
}
