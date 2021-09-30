package com.avaliation.poo.controller;


import com.avaliation.poo.model.Message;
import com.avaliation.poo.service.MessageService;
import com.avaliation.poo.viewmodel.MessageViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController extends BaseController{
    @Autowired
    private MessageService messageService;
    
    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageViewModel model) throws Exception {
        return messageService.createMessage(model);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable(value = "id") Long id) throws Exception {
        messageService.deleteMessage(id);
    }
}
