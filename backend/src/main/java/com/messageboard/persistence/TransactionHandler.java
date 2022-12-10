package com.messageboard.persistence;

import com.messageboard.utils.ThrowableSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class TransactionHandler {
    @PersistenceContext
    private final EntityManager entityManager;
    private EntityTransaction currentTransaction = null;

    @Autowired
    public TransactionHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> T encapsulateTransaction(ThrowableSupplier<T> callback) throws Exception {
        //EntityTransaction transaction = getTransaction();
        try {
           // transaction.begin();
            T result = callback.get();
           // transaction.commit();
            return result;
        } catch (Exception e) {
            //transaction.rollback();
            throw e;
        } finally {
            currentTransaction = null;
        }
    }

    private EntityTransaction getTransaction() {
        if (currentTransaction == null) currentTransaction = entityManager.getTransaction();
        return currentTransaction;
    }
}
