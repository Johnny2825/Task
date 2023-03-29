package com.example.task.service.product;

import com.example.task.controller.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
public interface ProductInternalService {

    Page<ProductDto> findAll(Pageable pageable);

    Optional<ProductDto> findById(@NonNull Long productId);

    void updateAverageScore(@NonNull Long productId);
}
