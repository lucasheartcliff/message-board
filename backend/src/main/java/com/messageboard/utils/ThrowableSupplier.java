package com.messageboard.utils;

@FunctionalInterface
public interface ThrowableSupplier<T> {
    public T get() throws Exception;
}
