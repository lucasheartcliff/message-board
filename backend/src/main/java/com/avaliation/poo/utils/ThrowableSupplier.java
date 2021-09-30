package com.avaliation.poo.utils;

@FunctionalInterface
public interface ThrowableSupplier<T> {
    public T get() throws Exception;
}
