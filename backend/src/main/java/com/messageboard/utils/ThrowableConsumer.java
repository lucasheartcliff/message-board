package com.messageboard.utils;
@FunctionalInterface
public interface ThrowableConsumer<T> {
    void run(T parameter) throws Exception;

}
