package com.example.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Класс шаблон для получения JSON.
 */

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

}
