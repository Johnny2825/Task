package com.example.task.service.comment;

import com.example.task.controller.dto.CommentDto;
import com.example.task.entity.CommentEntity;

/**
 * @author Tarkhov Evgeniy
 */
public class CommentTestData {

    private static final Long ID = 1L;
    private static final Long PRODUCT_ID = 10L;
    private static final String NAME = "Bob";
    private static final String CONTENT = "Good";
    private static final Byte RATING = (byte) 3;

    public CommentDto getCommentDto1() {
        return CommentDto.builder()
                .id(ID)
                .productId(PRODUCT_ID)
                .name(NAME)
                .content(CONTENT)
                .rating(RATING)
                .build();
    }

    public CommentDto getCommentDto2() {
        return CommentDto.builder()
                .id(2L)
                .productId(PRODUCT_ID)
                .name("John")
                .content("Well")
                .rating((byte) 4)
                .build();
    }

    public CommentEntity getCommentEntity1() {
        return CommentEntity.builder()
                .id(ID)
                .productId(PRODUCT_ID)
                .name(NAME)
                .content(CONTENT)
                .rating(RATING)
                .build();
    }

    public CommentEntity getCommentEntity2() {
        return CommentEntity.builder()
                .id(2L)
                .productId(PRODUCT_ID)
                .name("John")
                .content("Well")
                .rating((byte) 4)
                .build();
    }


    public CommentEntity getCommentEntityWithoutId() {
        return CommentEntity.builder()
                .productId(PRODUCT_ID)
                .name(NAME)
                .content(CONTENT)
                .rating(RATING)
                .build();
    }
}
