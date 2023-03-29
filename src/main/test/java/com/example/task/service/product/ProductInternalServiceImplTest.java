package com.example.task.service.product;

import com.example.task.controller.dto.CommentDto;
import com.example.task.controller.dto.ProductDto;
import com.example.task.entity.ProductEntity;
import com.example.task.mapper.ProductMapper;
import com.example.task.repository.ProductRepository;
import com.example.task.service.comment.CommentInternalService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Tarkhov Evgeniy
 */
@ExtendWith(MockitoExtension.class)
class ProductInternalServiceImplTest {
    private ProductTestData productTestData = new ProductTestData();
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CommentInternalService commentInternalService;
    private ProductInternalService service;

    @BeforeEach
    void setUp() {
        service = new ProductInternalServiceImpl(productRepository,
                productMapper,
                commentInternalService);
    }

    @Test
    void findAll() {
        ProductDto productDto1 = productTestData.getProductDto1();
        ProductDto productDto2 = productTestData.getProductDto2();
        List<ProductDto> expected = List.of(productDto1, productDto2);

        ProductEntity productEntity1 = productTestData.getProductEntity1();
        ProductEntity productEntity2 = productTestData.getProductEntity2();
        List<ProductEntity> productEntityList = List.of(productEntity1, productEntity2);

        when(productRepository.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(productEntityList));
        when(productMapper.map(any(ProductEntity.class))).thenAnswer(invocationOnMock -> {
            ProductEntity entity = invocationOnMock.getArgument(0);
            return ProductDto.builder()
                    .id(entity.getId())
                    .categoryId(entity.getCategoryId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .count(entity.getCount())
                    .price(entity.getPrice())
                    .averageRating(entity.getAverageRating())
                    .build();
        });

        Page<ProductDto> result = service.findAll(Pageable.unpaged());
        assertTrue(CollectionUtils.isEqualCollection(expected, result.getContent()));
    }

    @Test
    void findById() {
        Long productId = 1L;
        ProductEntity productEntity = productTestData.getProductEntity1();
        ProductDto expected = productTestData.getProductDto1();

        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(productEntity));
        when(productMapper.map(productEntity)).thenReturn(expected);

        Optional<ProductDto> result = service.findById(productId);
        assertThat(result)
                .matches(Optional::isPresent)
                .matches(v -> Objects.equals(expected, v.get()));
    }

    @Test
    void updateAverageScore_whenCannotFindProductById() {
        Long productId = 1L;
        service.updateAverageScore(productId);
        verify(commentInternalService, times(0)).findAllCommentByProduct(anyLong(), any());
    }

    @Test
    void updateAverageScore_successUpdateAverageScore() {
        Long productId = 1L;
        ProductEntity productEntity = productTestData.getProductEntity1();
        CommentDto commentDto1 = new CommentDto();
        commentDto1.setRating((byte) 2);
        CommentDto commentDto2 = new CommentDto();
        commentDto2.setRating((byte) 3);
        List<CommentDto> commentList = List.of(commentDto1, commentDto2);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(commentInternalService.findAllCommentByProduct(eq(productId), any())).thenReturn(new PageImpl<>(commentList));

        service.updateAverageScore(productId);
        verify(commentInternalService, times(1)).findAllCommentByProduct(eq(productId), any());
    }
}