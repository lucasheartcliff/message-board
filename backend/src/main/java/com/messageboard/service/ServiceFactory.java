package com.messageboard.service;

import com.messageboard.persistence.TransactionHandler;
import com.messageboard.repository.RepositoryFactory;
import com.messageboard.service.message.MessageService;
import com.messageboard.service.message.MessageServiceImpl;
import com.messageboard.service.user.UserService;
import com.messageboard.service.user.UserServiceImpl;
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
