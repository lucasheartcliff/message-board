package com.avaliation.poo.controller;


import com.avaliation.poo.model.Message;
import com.avaliation.poo.service.ServiceFactory;
import com.avaliation.poo.service.message.MessageServiceImpl;
import com.avaliation.poo.viewmodel.MessageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController extends BaseController{

    @Autowired
    public MessageController(ServiceFactory serviceFactory) {
        super(serviceFactory);
    }

    @GetMapping
    public List<Message> getMessages() {
        return serviceFactory.buildMessageService().getMessages();
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageViewModel model) throws Exception {
        return serviceFactory.buildMessageService().createMessage(model);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable(value = "id") Long id) throws Exception {
        serviceFactory.buildMessageService().deleteMessage(id);
    }
}
