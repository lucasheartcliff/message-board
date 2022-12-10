package com.messageboard.service.message;

import com.messageboard.model.Message;
import com.messageboard.viewmodel.MessageViewModel;

import java.util.List;

public interface MessageService {
    List<Message> getMessages();

    Message createMessage(MessageViewModel model) throws Exception;

    void deleteMessage(Long messageId) throws Exception;
}
