package com.messageboard.persistence;

import javax.persistence.EntityManagerFactory;

public interface PersistenceService {
    EntityManagerFactory buildEntityManagerFactory();
}
