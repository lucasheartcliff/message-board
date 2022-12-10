package com.avaliation.poo.service.message;

import com.avaliation.poo.model.Message;
import com.avaliation.poo.viewmodel.MessageViewModel;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();

    Message createMessage(MessageViewModel model) throws Exception;

    void deleteMessage(Long messageId) throws Exception;
}
