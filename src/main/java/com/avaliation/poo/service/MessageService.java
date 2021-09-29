package com.avaliation.poo.service;

import com.avaliation.poo.model.Message;
import com.avaliation.poo.model.User;
import com.avaliation.poo.repository.MessageRepository;
import com.avaliation.poo.repository.UserRepository;
import com.avaliation.poo.viewmodel.MessageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService extends BaseService{
    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private UserRepository userRepo;

    public List<Message> getMessages() {
        List<Message> result = messageRepo.findAll();
        return CollectionUtils.isEmpty(result) ? new ArrayList<>() : result;
    }

    public Message createMessage(MessageViewModel model) throws Exception {
        return encapsulateTransaction(() -> {
            Optional<User> optionalUser = userRepo.findById(model.getUserId());

            if(optionalUser.isPresent()){
                Message message = new Message();
                message.setMessage(model.getMessage());
                message.setUser(optionalUser.get());
                return messageRepo.save(message);
            }else throw new Exception("User not found");
        });
    }

    public void deleteMessage(Long messageId) throws Exception {
        encapsulateTransaction(() -> {
            messageRepo.deleteById(messageId);
            return null;
        });
    }
}
