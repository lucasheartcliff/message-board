package com.messageboard;

import com.messageboard.controller.ControllerFactory;
import com.messageboard.persistence.PersistenceService;
import com.messageboard.persistence.PersistenceServiceImpl;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PersistenceService persistenceService = new PersistenceServiceImpl();
        EntityManagerFactory entityManagerFactory = persistenceService.buildEntityManagerFactory();

        RequestHandler requestHandler = new RequestHandler(entityManagerFactory);
        ControllerFactory controllerFactory = new ControllerFactory(requestHandler);

        ServerService serverService = new ServerService(controllerFactory);
        serverService.start();
    }

}
