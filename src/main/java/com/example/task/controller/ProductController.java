package com.example.task.controller;

import com.example.task.entity.Product;
import com.example.task.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    Product findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @GetMapping("/product")
    List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/more")
    List<Product> findAllMoreThat(@RequestParam("value") String value) {
        return productService.findAllMoreThan(value);
    }

    @GetMapping("/test")
    List<Product> testEntity() {
        return productService.test();
    }
}
