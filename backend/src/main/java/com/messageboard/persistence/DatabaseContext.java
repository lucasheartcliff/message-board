package com.messageboard.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public interface DatabaseContext extends AutoCloseable {
    EntityManager getEntityManager();
}
