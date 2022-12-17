package com.messageboard.controller;

import com.messageboard.RequestHandler;

import javax.persistence.EntityManagerFactory;

public class ControllerFactory {
    private final RequestHandler requestHandler;

    public ControllerFactory(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public HomeController buildHomeController(){
        return new HomeController(requestHandler);
    }

    public UsersController buildUsersController(){
        return new UsersController(requestHandler);
    }

    public AuthController buildAuthController(){
        return new AuthController(requestHandler);
    }
    public MessageController buildMessageController(){
        return new MessageController(requestHandler);
    }



}
