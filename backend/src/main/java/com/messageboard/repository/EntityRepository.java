package com.messageboard.repository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<TEntity, TKey> {
    List<TEntity> findAll();

    <S extends TEntity> S save(S entity);

    void delete(TEntity entity);

    Optional<TEntity> findById(TKey id);

    void deleteById(TKey id);
}
