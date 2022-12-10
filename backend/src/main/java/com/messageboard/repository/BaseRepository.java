package com.messageboard.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class BaseRepository<TEntity, TKey extends Serializable> extends SimpleJpaRepository<TEntity, TKey> {
    protected final EntityManager entityManager;
    protected BaseRepository(EntityManager entityManager, Class<TEntity> clazz) {
        super(clazz, entityManager);
        this.entityManager = entityManager;
    }
}
