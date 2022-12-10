package com.avaliation.poo.service;

import com.avaliation.poo.persistence.TransactionHandler;
import com.avaliation.poo.repository.RepositoryFactory;

public abstract class BaseService {
    protected final RepositoryFactory repositoryFactory;
    protected final TransactionHandler transactionHandler;

    protected  BaseService(RepositoryFactory repositoryFactory, TransactionHandler transactionHandler) {
        this.repositoryFactory = repositoryFactory;
        this.transactionHandler = transactionHandler;
    }
}