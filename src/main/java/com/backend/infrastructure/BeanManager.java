package com.backend.infrastructure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class BeanManager {
    private static final EntityManagerFactory manager = Persistence.createEntityManagerFactory("persistence-unit");;
    private static EntityManager em;

    private BeanManager() {}

    public static EntityManager getEntityManager() {
        if (em == null) {
            em = manager.createEntityManager();
        }
        return em;
    }
}