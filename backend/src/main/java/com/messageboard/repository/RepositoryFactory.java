package com.messageboard.repository;

import com.messageboard.repository.message.MessageRepository;
import com.messageboard.repository.message.MessageRepositoryImpl;
import com.messageboard.repository.user.UserRepository;
import com.messageboard.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class RepositoryFactory {
    private final EntityManager entityManager;

    @Autowired
    public RepositoryFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserRepository buildUserRepository(){
        return new UserRepositoryImpl(entityManager);
    }

    public MessageRepository buildMessageRepository(){
        return new MessageRepositoryImpl(entityManager);
    }
}
