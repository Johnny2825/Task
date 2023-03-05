package com.example.task.mapper;

import com.example.task.controller.dto.CommentDto;
import com.example.task.entity.CommentEntity;

/**
 * @author Tarkhov Evgeniy
 */
public interface CommentMapper {

    CommentEntity map(Long productId, CommentDto commentDto);

    CommentDto map(CommentEntity commentEntity);
}
