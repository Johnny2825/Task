package com.example.task.mapper;

import com.example.task.controller.dto.CommentDto;
import com.example.task.entity.CommentEntity;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

/**
 * @author Tarkhov Evgeniy
 */
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity map(Long productId, CommentDto commentDto) {
        if (isNull(commentDto)) {
            return null;
        }
        return CommentEntity.builder()
                .productId(productId)
                .name(commentDto.getName())
                .content(commentDto.getContent())
                .rating(commentDto.getRating())
                .build();
    }

    @Override
    public CommentDto map(CommentEntity commentEntity) {
        if (isNull(commentEntity)) {
            return null;
        }
        return CommentDto.builder()
                .id(commentEntity.getId())
                .productId(commentEntity.getProductId())
                .name(commentEntity.getName())
                .content(commentEntity.getContent())
                .rating(commentEntity.getRating())
                .build();
    }
}
