package com.messageboard.service.message;

import com.messageboard.model.Message;
import com.messageboard.model.User;
import com.messageboard.persistence.TransactionHandler;
import com.messageboard.repository.RepositoryFactory;
import com.messageboard.service.BaseService;
import com.messageboard.viewmodel.MessageViewModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl extends BaseService implements MessageService {

    public MessageServiceImpl(RepositoryFactory repositoryFactory, TransactionHandler transactionHandler) {
        super(repositoryFactory, transactionHandler);
    }

    @Override
    public List<Message> getMessages() {
        List<Message> result = repositoryFactory.buildMessageRepository().findAll();
        return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
    }
    @Override
    public Message createMessage(MessageViewModel model) throws Exception {
        return transactionHandler.encapsulateTransaction(() -> {
            Optional<User> optionalUser = repositoryFactory.buildUserRepository().findById(model.getUserId());

            if (optionalUser.isPresent()) {
                Message message = new Message();
                message.setMessage(model.getMessage());
                message.setUser(optionalUser.get());
                return repositoryFactory.buildMessageRepository().save(message);
            } else throw new Exception("User not found");
        });
    }
    @Override
    public void deleteMessage(Long messageId) throws Exception {
        transactionHandler.encapsulateTransaction(() -> {
            repositoryFactory.buildMessageRepository().deleteById(messageId);
            return null;
        });
    }
}
