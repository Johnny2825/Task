package com.example.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    private String name;
    @NotBlank
    private String content;
    @Min(1)
    @Max(5)
    private Byte rating;
}
