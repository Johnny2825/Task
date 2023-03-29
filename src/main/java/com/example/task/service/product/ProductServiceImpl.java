package com.example.task.service.product;

import com.example.task.controller.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductInternalService productInternalService;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return productInternalService.findAll(pageable);
    }

    @Override
    public Optional<ProductDto> findById(Long productId) {
        return productInternalService.findById(productId);
    }
}
