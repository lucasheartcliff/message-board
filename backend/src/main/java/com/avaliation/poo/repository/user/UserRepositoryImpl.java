package com.avaliation.poo.repository.user;

import com.avaliation.poo.model.User;
import com.avaliation.poo.model.User_;
import com.avaliation.poo.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository<User, Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public User findUserByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(getDomainClass());
        Root<User> root = query.from(getDomainClass());

        query.where(cb.equal(root.get(User_.email), email));
        List<User> resultList = entityManager.createQuery(query).getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
