package com.example.task.dao;

import com.example.task.dto.ProductWithRating;
import com.example.task.entity.Category;
import com.example.task.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

/**
 * Класс для формирования запросов к таблице "Продукт".
 */

@Repository
public class ProductDAO {
    private EntityManager em;

    public ProductDAO() {
        this.em = new SessionFactory().getEm();
    }

    public List<Product> findAll(){
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = query.getResultList();
        em.getTransaction().commit();
        return products;
    }

    public List<Product> findByCategoryName(String name) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Product p LEFT JOIN p.categories c WHERE c.name = :name");
        query.setParameter("name", name);
        List<Product> products = query.getResultList();
        em.getTransaction().commit();
        return products;
    }

    public Double avgRating(UUID id) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT avg(c.rating) from Comment c WHERE c.product.id = :id");
        query.setParameter("id", id);
        Double averageRating = (Double) query.getSingleResult();
        em.getTransaction().commit();
        if (averageRating == null)
            return 0.0;
        return averageRating;
    }

    public Product findById(UUID id) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class);
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        em.getTransaction().commit();
        return product;
    }

    public void updateCountById(UUID id, long count) {
        Query query = em.createQuery("select p from Product p where p.id = :id");
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        product.setCount(product.getCount() - count);
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }
}
