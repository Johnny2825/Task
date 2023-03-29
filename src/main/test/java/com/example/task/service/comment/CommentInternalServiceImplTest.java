package com.example.task.service.comment;

import com.example.task.controller.dto.CommentDto;
import com.example.task.entity.CommentEntity;
import com.example.task.mapper.CommentMapper;
import com.example.task.repository.CommentRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Tarkhov Evgeniy
 */
@ExtendWith(MockitoExtension.class)
class CommentInternalServiceImplTest {
    CommentTestData commentTestData = new CommentTestData();
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentMapper commentMapper;
    private CommentInternalService service;

    @BeforeEach
    void setUp() {
        service = new CommentInternalServiceImpl(commentRepository,
                commentMapper);
    }

    @Test
    void findAllCommentByProduct() {
        Long productId = 1L;
        CommentDto commentDto1 = commentTestData.getCommentDto1();
        CommentDto commentDto2 = commentTestData.getCommentDto2();
        List<CommentDto> expected = List.of(commentDto1, commentDto2);

        CommentEntity commentEntity1 = commentTestData.getCommentEntity1();
        CommentEntity commentEntity2 = commentTestData.getCommentEntity2();
        List<CommentEntity> commentEntityList = List.of(commentEntity1, commentEntity2);

        when(commentRepository.findAllByProductId(productId, Pageable.unpaged())).thenReturn(commentEntityList);
        when(commentMapper.map(any(CommentEntity.class))).thenAnswer(invocationOnMock -> {
            CommentEntity entity = invocationOnMock.getArgument(0);
            return CommentDto.builder()
                    .id(entity.getId())
                    .productId(entity.getProductId())
                    .name(entity.getName())
                    .content(entity.getContent())
                    .rating(entity.getRating())
                    .build();
        });

        Page<CommentDto> result = service.findAllCommentByProduct(productId, Pageable.unpaged());
        assertTrue(CollectionUtils.isEqualCollection(expected, result.getContent()));
    }

    @Test
    void findByCommentId() {
        Long commentId = 1L;
        CommentDto expected = commentTestData.getCommentDto1();
        CommentEntity entityOutput = commentTestData.getCommentEntity1();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(entityOutput));
        when(commentMapper.map(entityOutput)).thenReturn(expected);
        
        Optional<CommentDto> result = service.findByCommentId(commentId);
        assertThat(result)
                .isPresent()
                .matches(e -> Objects.equals(expected, e.get()));
    }

    @Test
    void createComment() {
        Long productId = 1L;
        CommentDto input = commentTestData.getCommentDto1();
        CommentEntity entityInput = commentTestData.getCommentEntityWithoutId();
        CommentEntity entityOutput = commentTestData.getCommentEntity1();

        CommentDto expected = commentTestData.getCommentDto1();

        when(commentRepository.save(entityInput)).thenReturn(entityOutput);
        when(commentMapper.map(productId, input)).thenReturn(entityInput);
        when(commentMapper.map(entityOutput)).thenReturn(expected);

        Optional<CommentDto> result = service.createComment(productId, input);
        assertThat(result)
                .isPresent()
                .matches(e -> Objects.equals(expected, e.get()))
                .matches(e -> Objects.equals(expected.getId(), e.get().getId()));
    }

    @Test
    void updateComment_successUpdate() {
        Long commentId = 1L;
        CommentDto commentDto = commentTestData.getCommentDto1();
        CommentEntity commentEntity = commentTestData.getCommentEntity1();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentEntity));

        Optional<CommentDto> result = service.updateComment(commentId, commentDto);
        assertThat(result)
                .isNotEmpty()
                .matches(e -> Objects.equals(commentDto, e.get()));
    }

    @Test
    void updateComment_whenCommentNotFound() {
        Long commentId = 1L;
        CommentDto commentDto = commentTestData.getCommentDto1();

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        Optional<CommentDto> result = service.updateComment(commentId, commentDto);
        assertThat(result)
                .isEmpty();
    }

    @Test
    void deleteComment_success() {
        Long commentId = 1L;
        CommentEntity entity = commentTestData.getCommentEntity1();
        CommentDto expected = commentTestData.getCommentDto1();

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(entity));
        when(commentMapper.map(entity)).thenReturn(expected);

        Optional<CommentDto> result = service.deleteComment(commentId);
        verify(commentRepository, times(1)).delete(entity);
        assertThat(result)
                .isNotEmpty()
                .matches(e -> Objects.equals(expected, e.get()));
    }

    @Test
    void deleteComment_whenCommentNotFound() {
        Long commentId = 1L;
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());
        Optional<CommentDto> result = service.deleteComment(commentId);
        assertThat(result)
                .isEmpty();
    }
}