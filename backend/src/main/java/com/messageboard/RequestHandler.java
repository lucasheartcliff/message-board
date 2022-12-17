package com.messageboard;

import com.messageboard.persistence.DatabaseContext;
import com.messageboard.persistence.DatabaseContextImpl;
import com.messageboard.repository.RepositoryFactory;
import com.messageboard.service.ServiceFactory;

import javax.persistence.EntityManagerFactory;

public class RequestHandler {
    private final EntityManagerFactory entityManagerFactory;


    public RequestHandler(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public DatabaseContext buildDatabaseContext() {
        return new DatabaseContextImpl(entityManagerFactory);
    }

    private RepositoryFactory buildRepositoryFactory(DatabaseContext databaseContext) {
        return new RepositoryFactory(databaseContext);
    }


    public ServiceFactory buildServiceFactory(DatabaseContext databaseContext) {
        RepositoryFactory repositoryFactory = buildRepositoryFactory(databaseContext);
        return new ServiceFactory(repositoryFactory, databaseContext);
    }

}