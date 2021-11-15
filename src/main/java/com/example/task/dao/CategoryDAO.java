package com.example.task.dao;

import com.example.task.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Класс для формирования запросов к таблице "Категория".
 */

@Repository
public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO() {
        this.em = new SessionFactory().getEm();
    }

    public Category findByName(String name) {
        em.getTransaction().begin();
        Query query = em.createQuery("select c from Category c where c.name = :name");
        query.setParameter("name", name);
        Category category = (Category) query.getSingleResult();
        em.getTransaction().commit();
        return category;
    }
}
