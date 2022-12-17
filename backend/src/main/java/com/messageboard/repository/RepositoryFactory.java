package com.messageboard.repository;

import com.messageboard.persistence.DatabaseContext;
import com.messageboard.repository.message.MessageRepository;
import com.messageboard.repository.message.MessageRepositoryImpl;
import com.messageboard.repository.user.UserRepository;
import com.messageboard.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class RepositoryFactory {
    private final DatabaseContext databaseContext;

    public RepositoryFactory(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    public UserRepository buildUserRepository(){
        return new UserRepositoryImpl(databaseContext.getEntityManager());
    }

    public MessageRepository buildMessageRepository(){
        return new MessageRepositoryImpl(databaseContext.getEntityManager());
    }
}
