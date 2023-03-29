package com.example.task.service.comment;

import com.example.task.controller.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Tarkhov Evgeniy
 */
public interface CommentService {

    Page<CommentDto> findAllCommentByProduct(Long productId, Pageable pageable);

    Optional<CommentDto> findByCommentId(Long commentId);

    Optional<CommentDto> createComment(Long productId, CommentDto commentDto);

    Optional<CommentDto> updateComment(Long commentId, CommentDto commentDto);

    Optional<CommentDto> deleteComment(Long commentId);
}
