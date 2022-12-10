package com.messageboard.repository.message;

import com.messageboard.model.Message;
import com.messageboard.repository.BaseRepository;

import javax.persistence.EntityManager;

public class MessageRepositoryImpl extends BaseRepository<Message, Long> implements MessageRepository {

    public MessageRepositoryImpl(EntityManager entityManager) {
        super(entityManager, Message.class);
    }
}
