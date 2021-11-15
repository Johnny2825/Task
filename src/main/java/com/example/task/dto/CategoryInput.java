package com.example.task.dto;

/**
 * Класс шаблон для получения JSON.
 */

public class CategoryInput {
    private String name;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
