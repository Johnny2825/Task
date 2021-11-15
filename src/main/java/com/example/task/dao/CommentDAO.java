package com.example.task.dao;

import com.example.task.entity.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

/**
 * Класс для формирования запросов к таблице "Комментарий".
 */

@Repository
public class CommentDAO {
    private EntityManager em;

    public CommentDAO() {
        this.em = new SessionFactory().getEm();
    }

    public Comment save(Comment comment) {
        em.getTransaction().begin();
        em.persist(comment);
        em.getTransaction().commit();
        return comment;
    }

    public List<Comment> findAllByProductId(UUID id) {
        em.getTransaction().begin();
        Query query = em.createQuery("select c from Comment c where c.product.id = :id");
        query.setParameter("id", id);
        List<Comment> comments = query.getResultList();
        em.getTransaction().commit();
        return comments;
    }
}
