package com.example.task.dto.mapper;

import com.example.task.dto.CommentInput;
import com.example.task.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    Comment toComment(CommentInput commentInput);

    @InheritInverseConfiguration
    CommentInput commentInput(Comment comment);
}
