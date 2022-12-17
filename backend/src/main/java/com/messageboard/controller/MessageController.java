package com.messageboard.controller;


import com.messageboard.RequestHandler;
import com.messageboard.model.Message;
import com.messageboard.viewmodel.MessageViewModel;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/api/messages")
public class MessageController extends BaseController {
    public MessageController(RequestHandler requestHandler) {
        super(requestHandler);
    }

    @GET
    public List<Message> getMessages() {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildMessageService().getMessages();
        });
    }

    @POST
    public Message createMessage(MessageViewModel model) throws Exception {
        return encapsulateRequest((serviceFactory) -> {
            return serviceFactory.buildMessageService().createMessage(model);
        });
    }

    @DELETE
    @Path("{id}")
    public void deleteMessage(@PathParam(value = "id") Long id) throws Exception {
        encapsulateRequest((serviceFactory) -> {
            serviceFactory.buildMessageService().deleteMessage(id);
        });
    }
}
