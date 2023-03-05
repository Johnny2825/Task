package com.example.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Tarkhov Evgeniy
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long productId;
    private String name;
    private String content;
    private Byte rating;
}
