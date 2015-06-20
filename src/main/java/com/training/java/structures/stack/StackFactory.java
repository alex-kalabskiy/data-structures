package com.training.java.structures.stack;

/**
 *
 *
 * Created by Alex on 09.05.2015.
 */
public class StackFactory<T> {

    public static final String LINKED_STACK = "Linked";
    public static final String ARRAY_STACK = "Array";

    public StackI<T> createStack(String stackType) {
        if (LINKED_STACK.equals(stackType)) {
            return new LinkedStack<>();
        }
        else if (ARRAY_STACK.equals(stackType)) {
            return new ArrayStack<>();
        }
        throw new IllegalArgumentException("Not supported stack type: " + stackType);
    }

    public StackI<T> createStack(String stackType, int capacity) {
        if (LINKED_STACK.equals(stackType)) {
            return new LinkedStack<>();
        }
        else if (ARRAY_STACK.equals(stackType)) {
            return new ArrayStack<>(capacity);
        }
        throw new IllegalArgumentException("Not supported stack type: " + stackType);
    }
    
    
}
