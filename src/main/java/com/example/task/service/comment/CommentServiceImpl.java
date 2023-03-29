package com.example.task.service.comment;

import com.example.task.controller.dto.CommentDto;
import com.example.task.controller.dto.ProductDto;
import com.example.task.service.product.ProductInternalService;
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
public class CommentServiceImpl implements CommentService {

    private final CommentInternalService commentInternalService;
    private final ProductInternalService productInternalService;

    @Override
    public Page<CommentDto> findAllCommentByProduct(Long productId, Pageable pageable) {
        return commentInternalService.findAllCommentByProduct(productId, pageable);
    }

    @Override
    public Optional<CommentDto> findByCommentId(Long commentId) {
        return commentInternalService.findByCommentId(commentId);
    }

    @Override
    public Optional<CommentDto> createComment(Long productId, CommentDto commentDto) {
        Optional<ProductDto> productDtoOptional = productInternalService.findById(productId);
        if (productDtoOptional.isEmpty()) {
            return Optional.empty();
        }
        Optional<CommentDto> commentDtoOptional = commentInternalService.createComment(productId, commentDto);
        commentDtoOptional.ifPresent(dto -> productInternalService.updateAverageScore(dto.getProductId()));
        return commentDtoOptional;
    }

    @Override
    public Optional<CommentDto> updateComment(Long commentId, CommentDto commentDto) {
        Optional<CommentDto> commentDtoOptional = commentInternalService.updateComment(commentId, commentDto);
        commentDtoOptional.ifPresent(dto -> productInternalService.updateAverageScore(dto.getProductId()));
        return commentDtoOptional;
    }

    @Override
    public Optional<CommentDto> deleteComment(Long commentId) {
        Optional<CommentDto> commentDtoOptional = commentInternalService.deleteComment(commentId);
        commentDtoOptional.ifPresent(dto -> productInternalService.updateAverageScore(dto.getProductId()));
        return commentDtoOptional;
    }
}
