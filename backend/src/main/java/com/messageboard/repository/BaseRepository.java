package com.messageboard.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<TEntity, TKey extends Serializable> implements EntityRepository<TEntity,TKey> {
    protected final EntityManager entityManager;
    protected final Class<TEntity> clazz;

    protected BaseRepository(EntityManager entityManager, Class<TEntity> clazz) {
       // super(clazz, entityManager);
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    @Override
    public List<TEntity> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = cb.createQuery(getDomainClass());
        Root<TEntity> root = query.from(getDomainClass());

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public <S extends TEntity> S save(S entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void delete(TEntity entity) {
        if (entity == null) return;
        entityManager.remove(entity);
    }

    @Override
    public Optional<TEntity> findById(TKey id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public void deleteById(TKey id) {
        Optional<TEntity> entityOptional = findById(id);
        entityOptional.ifPresent(this::delete);
    }

    protected Class<TEntity> getDomainClass() {
        return clazz;
    }
}
