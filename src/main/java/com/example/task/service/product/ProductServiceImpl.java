package com.example.task.service.product;

import com.example.task.controller.dto.CommentDto;
import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;
import com.example.task.mapper.ProductMapper;
import com.example.task.model.ProductWithRatingDto;
import com.example.task.repository.ProductRepository;
import com.example.task.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CommentService commentService;


    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        List<ProductDto> productDtoList = productRepository.findAll(pageable)
                .stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtoList);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        return productEntityOptional.map(productMapper::map);
    }

    @Override
    public Page<ProductWithRatingDto> findAllWithRating(Pageable pageable) {
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        List<ProductWithRatingDto> productWithRatingDtoList = productEntityPage
                .stream()
                .map(this::buildProductWithRating)
                .collect(Collectors.toList());
        return new PageImpl<>(productWithRatingDtoList);
    }

    private ProductWithRatingDto buildProductWithRating(ProductEntity productEntity) {
        Page<CommentDto> commentPage = commentService.findAllCommentByProduct(productEntity.getId(), Pageable.unpaged());
        BigDecimal averageRating = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(commentPage.getContent())) {
            BigDecimal sum = new BigDecimal(0);
            for (int i = 0; i < commentPage.getTotalElements(); i++) {
                sum = sum.add(BigDecimal.valueOf(commentPage.getContent().get(i).getRating()));
            }
//            commentPage.forEach(e -> sum.add(BigDecimal.valueOf(e.getRating())));
            BigDecimal elementsAmount = BigDecimal.valueOf(commentPage.getTotalElements());
            averageRating = sum.divide(elementsAmount, 2, RoundingMode.HALF_UP);
        }
        return ProductWithRatingDto.builder()
                .id(productEntity.getId())
                .categoryId(productEntity.getCategoryId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .count(productEntity.getCount())
                .price(productEntity.getPrice())
                .averageRating(averageRating)
                .build();
    }
}
