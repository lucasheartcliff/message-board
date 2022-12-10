package com.avaliation.poo.repository.message;

import com.avaliation.poo.model.Message;
import com.avaliation.poo.repository.BaseRepository;

import javax.persistence.EntityManager;

public class MessageRepositoryImpl extends BaseRepository<Message, Long> implements MessageRepository {

    public MessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Message.class);
    }
}
