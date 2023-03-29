package com.example.task.service.product;

import com.example.task.controller.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
public interface ProductInternalService {

    Page<ProductDto> findAll(Pageable pageable);

    Optional<ProductDto> findById(Long productId);

    void updateAverageScore(Long productId);
}
