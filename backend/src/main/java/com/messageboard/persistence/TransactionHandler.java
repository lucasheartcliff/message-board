package com.messageboard.persistence;

import com.messageboard.utils.ThrowableSupplier;
import org.hibernate.TransactionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionHandler {
    private final EntityManager entityManager;
    private EntityTransaction currentTransaction = null;


    public TransactionHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public <T> T encapsulateTransaction(ThrowableSupplier<T> callback) throws Exception {
        EntityTransaction transaction = getTransaction();
        try {
            transaction.begin();
            T result = callback.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            currentTransaction = null;
        }
    }
    
    public void flush(){
        if (currentTransaction == null || !currentTransaction.isActive()) throw new TransactionException("The transaction should be initiated before \"flush()\" method call");
        currentTransaction.commit();
        entityManager.flush();
    }

    private EntityTransaction getTransaction() {
        if (currentTransaction == null) currentTransaction = entityManager.getTransaction();
        return currentTransaction;
    }
}
