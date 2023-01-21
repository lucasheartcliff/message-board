package com.messageboard.persistence;

import com.messageboard.utils.ThrowableSupplier;
import org.hibernate.TransactionException;

import javax.persistence.EntityTransaction;

public class TransactionHandler {
    private final DatabaseContext databaseContext;
    private EntityTransaction currentTransaction = null;

    public TransactionHandler(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    public <T> T encapsulateTransaction(ThrowableSupplier<T> callback) throws Exception {
        EntityTransaction transaction = getTransaction();
        try {
            if (!transaction.isActive()) transaction.begin();
            T result = callback.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
        }
    }

    public void flush() {
        if (currentTransaction == null || !currentTransaction.isActive())
            throw new TransactionException("The transaction should be initiated before \"flush()\" method call");
        currentTransaction.commit();
        databaseContext.getEntityManager().flush();
    }

    private EntityTransaction getTransaction() {
        if (currentTransaction == null)
            currentTransaction = databaseContext.getEntityManager().getTransaction();
        return currentTransaction;
    }
}
