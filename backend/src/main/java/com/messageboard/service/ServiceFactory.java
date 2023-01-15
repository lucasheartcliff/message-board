package com.messageboard.service;

import com.messageboard.persistence.DatabaseContext;
import com.messageboard.persistence.TransactionHandler;
import com.messageboard.repository.RepositoryFactory;
import com.messageboard.service.message.MessageService;
import com.messageboard.service.message.MessageServiceImpl;
import com.messageboard.service.user.UserService;
import com.messageboard.service.user.UserServiceImpl;

public class ServiceFactory {
    private final RepositoryFactory repositoryFactory;
    private final DatabaseContext databaseContext;

    private TransactionHandler cachedTransactionHandler;

    public ServiceFactory(RepositoryFactory repositoryFactory, DatabaseContext  databaseContext) {
        this.repositoryFactory = repositoryFactory;
        this.databaseContext = databaseContext;
    }

    public UserService buildUserService() {
        return new UserServiceImpl(repositoryFactory, getTransactionHandler());
    }

    public MessageService buildMessageService() {
        return new MessageServiceImpl(repositoryFactory, getTransactionHandler());
    }

    private TransactionHandler getTransactionHandler(){
        if(cachedTransactionHandler == null) cachedTransactionHandler = new TransactionHandler(databaseContext);
        return cachedTransactionHandler;
    }
}
