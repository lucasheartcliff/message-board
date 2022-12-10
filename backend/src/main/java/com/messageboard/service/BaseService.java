package com.messageboard.service;

import com.messageboard.persistence.TransactionHandler;
import com.messageboard.repository.RepositoryFactory;

public abstract class BaseService {
    protected final RepositoryFactory repositoryFactory;
    protected final TransactionHandler transactionHandler;

    protected  BaseService(RepositoryFactory repositoryFactory, TransactionHandler transactionHandler) {
        this.repositoryFactory = repositoryFactory;
        this.transactionHandler = transactionHandler;
    }
}