package com.example.task.dto;

/**
 * Класс шаблон для получения JSON.
 */

public class CommentInput {

    private String name;
    private String content;
    private byte rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

}
