package com.example.task.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс шаблон для получения JSON.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private String currency;

}
