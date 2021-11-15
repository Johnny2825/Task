package com.example.task.service;

import com.example.task.dao.CommentDAO;
import com.example.task.entity.Comment;
import org.springframework.stereotype.Service;

/**
 * Класс сервис для работы с комментариями.
 */
@Service
public class CommentService {

    private final CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    /**
     * Сохранение комментария.
     *
     * @param comment - объект типа Comment для сохранения в базе данных.
     * @returns Comment - сохраненный объект.
     */

    public Comment save(Comment comment) {
        return commentDAO.save(comment);
    }
}
