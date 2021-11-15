package com.example.task.dao;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Класс для настройки EntityManagerFactory и получения объекта EntityManager.
 */

public class SessionFactory {
    private final EntityManagerFactory factory;
    private final EntityManager em;

    public SessionFactory() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        em = factory.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }
}
