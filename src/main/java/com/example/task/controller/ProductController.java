package com.example.task.controller;

import com.example.task.controller.dto.ProductDto;
import com.example.task.model.ProductWithRatingDto;
import com.example.task.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/with-rating")
    ResponseEntity<Page<ProductWithRatingDto>> findAllWithRating(@RequestParam(value = "size", required = false) Integer size,
                                                                 @RequestParam(value = "page", required = false) Integer page,
                                                                 Pageable pageable) {
        return ResponseEntity.ok(productService.findAllWithRating(pageable));
    }

    @GetMapping("/products")
    ResponseEntity<Page<ProductDto>> findAll(@RequestParam(value = "size", required = false) Integer size,
                                             @RequestParam(value = "page", required = false) Integer page,
                                             Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());

//        return ResponseEntity.of(productService.findById(id));
    }
}
