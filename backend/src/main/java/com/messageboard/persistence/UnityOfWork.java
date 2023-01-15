package com.messageboard.persistence;

import com.messageboard.utils.ThrowableSupplier;

public interface UnityOfWork {
    <T> T encapsulateTransaction(ThrowableSupplier<T> callback) throws Exception;

    void flush();
}
