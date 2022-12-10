package com.avaliation.poo.service;

import com.avaliation.poo.persistence.TransactionHandler;
import com.avaliation.poo.repository.RepositoryFactory;
import com.avaliation.poo.service.message.MessageService;
import com.avaliation.poo.service.message.MessageServiceImpl;
import com.avaliation.poo.service.user.UserService;
import com.avaliation.poo.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceFactory {
    private final RepositoryFactory repositoryFactory;
    private final TransactionHandler transactionHandler;

    @Autowired
    public ServiceFactory(RepositoryFactory repositoryFactory, TransactionHandler transactionHandler) {
        this.repositoryFactory = repositoryFactory;
        this.transactionHandler = transactionHandler;
    }

    public UserService buildUserService() {
        return new UserServiceImpl(repositoryFactory, transactionHandler);
    }

    public MessageService buildMessageService() {
        return new MessageServiceImpl(repositoryFactory, transactionHandler);
    }
}
