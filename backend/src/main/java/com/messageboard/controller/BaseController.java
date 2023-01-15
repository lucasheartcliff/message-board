package com.messageboard.controller;

import com.messageboard.RequestHandler;
import com.messageboard.persistence.DatabaseContext;
import com.messageboard.service.ServiceFactory;
import com.messageboard.utils.ThrowableConsumer;
import com.messageboard.utils.ThrowableFunction;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public abstract class BaseController {
  private final RequestHandler requestHandler;

  protected BaseController(RequestHandler requestHandler) {
    this.requestHandler = requestHandler;
  }

  protected <T> T encapsulateRequest(ThrowableFunction<ServiceFactory, T> function) {
    try (DatabaseContext databaseContext = requestHandler.buildDatabaseContext();) {
      ServiceFactory serviceFactory = requestHandler.buildServiceFactory(databaseContext);
      return function.apply(serviceFactory);
    } catch (Exception e) {
      System.out.println(e);
      // log error
    }
    return null;
  }

  protected void encapsulateRequest(ThrowableConsumer<ServiceFactory> function) {
    try (DatabaseContext databaseContext = requestHandler.buildDatabaseContext();) {
      ServiceFactory serviceFactory = requestHandler.buildServiceFactory(databaseContext);
      function.run(serviceFactory);
    } catch (Exception e) {
      System.out.println(e);
      // log error
    }
  }

}
