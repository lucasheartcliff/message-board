package com.avaliation.poo.repository;

import com.avaliation.poo.repository.message.MessageRepository;
import com.avaliation.poo.repository.message.MessageRepositoryImpl;
import com.avaliation.poo.repository.user.UserRepository;
import com.avaliation.poo.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
