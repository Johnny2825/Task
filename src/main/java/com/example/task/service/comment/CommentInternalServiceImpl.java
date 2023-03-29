package com.example.task.service.comment;

import com.example.task.controller.dto.CommentDto;
import com.example.task.entity.CommentEntity;
import com.example.task.mapper.CommentMapper;
import com.example.task.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для работы с комментариями.
 */
@Service
@RequiredArgsConstructor
public class CommentInternalServiceImpl implements CommentInternalService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Page<CommentDto> findAllCommentByProduct(Long productId, Pageable pageable) {
        List<CommentDto> commentDtoList = commentRepository.findAllByProductId(productId, pageable)
                .stream()
                .map(commentMapper::map)
                .collect(Collectors.toList());
        return new PageImpl<>(commentDtoList, pageable, commentDtoList.size());
    }

    @Override
    public Optional<CommentDto> findByCommentId(Long commentId) {
         Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
         return commentOptional.map(commentMapper::map);
    }

    @Override
    public Optional<CommentDto> createComment(Long productId, CommentDto commentDto) {
        CommentEntity commentEntitySaved = commentRepository.save(commentMapper.map(productId, commentDto));
        CommentDto commentDtoSaved = commentMapper.map(commentEntitySaved);
        return Optional.of(commentDtoSaved);
    }

    @Override
    @Transactional
    public Optional<CommentDto> updateComment(Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            CommentEntity commentEntity = commentOptional.get();
            commentEntity.setContent(commentDto.getContent());
            commentEntity.setRating(commentDto.getRating());
            return Optional.of(commentDto);
        }
        return Optional.empty();
    }

    @Override
    public Optional<CommentDto> deleteComment(Long commentId) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            CommentEntity commentEntity = commentOptional.get();
            commentRepository.delete(commentEntity);
            return commentOptional.map(commentMapper::map);
        }
        return Optional.empty();
    }
}
