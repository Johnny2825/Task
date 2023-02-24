package com.example.task.service.comment;

import com.example.task.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Класс сервис для работы с комментариями.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

//    /**
//     * Сохранение комментария.
//     *
//     * @param comment - объект типа Comment для сохранения в базе данных.
//     * @returns Comment - сохраненный объект.
//     */
//
//    public Comment save(Comment comment) {
//        return commentRepository.save(comment);
//    }
}
