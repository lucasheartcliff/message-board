package com.messageboard.utils;

@FunctionalInterface
public interface ThrowableFunction<T, R> {
    R apply(T parameter) throws Exception;
}
