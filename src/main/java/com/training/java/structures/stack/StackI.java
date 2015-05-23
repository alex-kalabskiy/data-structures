package com.training.java.structures.stack;

import com.training.java.service.Predicate;

/**
 *
 * Created by Alex on 14.03.15.
 */
public interface StackI <T> {
    boolean empty();

    int size();

    void push(T item);

    T pop();

    void pushAll(StackI<? extends T> stack);

    StackI<T> getAll(Predicate<? super T> predicate);
}
