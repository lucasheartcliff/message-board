package com.avaliation.poo.service;

import com.avaliation.poo.utils.ThrowableSupplier;

import java.util.function.Function;
import java.util.function.Supplier;

public class BaseService {
    protected <T> T encapsulateTransaction(ThrowableSupplier<T> callback) throws Exception{
        try {
            return callback.get();
        } catch (Exception e) {
            throw e;
        }
    }
}