package com.messageboard;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.messageboard.persistence.PersistenceService;
import com.messageboard.persistence.PersistenceServiceImpl;

public class ServletInitializer extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    PersistenceService persistenceService = new PersistenceServiceImpl();
    EntityManagerFactory entityManagerFactory = persistenceService.buildEntityManagerFactory();

    RequestHandler requestHandler = new RequestHandler(entityManagerFactory);

    ConfigurableApplicationContext context = application.context();
    AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
    autowireCapableBeanFactory.autowireBean(requestHandler);

    return application.sources(Main.class);
  }
}
