package com.example.task.service.product;

import com.example.task.controller.dto.CommentDto;
import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;
import com.example.task.mapper.ProductMapper;
import com.example.task.repository.ProductRepository;
import com.example.task.service.comment.CommentInternalService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс сервис для работы с продуктами.
 */

@Service
@RequiredArgsConstructor
public class ProductInternalServiceImpl implements ProductInternalService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CommentInternalService commentInternalService;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        List<ProductDto> productDtoList = productRepository.findAll(pageable)
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtoList);
    }

    @Override
    public Optional<ProductDto> findById(final Long productId) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        return productEntityOptional.map(productMapper::map);
    }

    @Override
    @Transactional
    public void updateAverageScore(final Long productId) {
        final Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        if (productEntityOptional.isEmpty()) {
            return;
        }
        final ProductEntity productEntity = productEntityOptional.get();
        Page<CommentDto> commentPage = commentInternalService.findAllCommentByProduct(productEntity.getId(), Pageable.unpaged());
        if (CollectionUtils.isNotEmpty(commentPage.getContent())) {
            Integer sum = 0;
            for (CommentDto commentDto : commentPage.getContent()) {
                sum += commentDto.getRating();
            }
            final BigDecimal sumElement = BigDecimal.valueOf(sum);
            final BigDecimal elementsAmount = BigDecimal.valueOf(commentPage.getTotalElements());
            productEntity.setAverageRating(sumElement.divide(elementsAmount, 2, RoundingMode.HALF_UP));
        }
    }
}
