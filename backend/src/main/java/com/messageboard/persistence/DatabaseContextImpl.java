package com.messageboard.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DatabaseContextImpl implements DatabaseContext {
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager cachedEntityManager;

    public DatabaseContextImpl(EntityManagerFactory buildEntityManagerFactory) {
        this.entityManagerFactory = buildEntityManagerFactory;
    }

    @Override
    public EntityManager getEntityManager() {
        if (cachedEntityManager == null) cachedEntityManager = entityManagerFactory.createEntityManager();
        return cachedEntityManager;
    }

    @Override
    public void close() throws Exception {
        if (cachedEntityManager != null) cachedEntityManager.close();
    }
}
