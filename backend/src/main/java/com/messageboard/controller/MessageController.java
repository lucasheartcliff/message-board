package com.messageboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messageboard.RequestHandler;
import com.messageboard.model.Message;
import com.messageboard.viewmodel.MessageViewModel;

@RestController
@RequestMapping("/api/messages")
public class MessageController extends BaseController {

  @Autowired
  public MessageController(RequestHandler requestHandler) {
    super(requestHandler);
  }

  @GetMapping
  public List<Message> getMessages() {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildMessageService().getMessages();
    });
  }

  @PostMapping
  public Message createMessage(@RequestBody MessageViewModel model) throws Exception {
    return encapsulateRequest((serviceFactory) -> {
      return serviceFactory.buildMessageService().createMessage(model);
    });
  }

  @DeleteMapping("/{id}")
  public void deleteMessage(@PathVariable(value = "id") Long id) throws Exception {
    encapsulateRequest((serviceFactory) -> {
      serviceFactory.buildMessageService().deleteMessage(id);
    });
  }
}
