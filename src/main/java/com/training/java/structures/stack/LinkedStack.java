package com.training.java.structures.stack;

import com.training.java.service.Predicate;

/**
 * Creates LinkedStack.
 * @author Alex
 * @since 22.03.15.
 */
public class LinkedStack <T> implements StackI <T> {
    private Node<T> top = new Node<>();
    private int count;

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void push(T item) {
        if (item != null) {
            top = new Node<>(item, top);
            count++;
        }
    }

    //todo save order of stack

    @Override
    public void pushAll(StackI<? extends T> stack) {
        StackI<T> tempStack = new LinkedStack<>();
        while (!stack.empty()) {
            T item = stack.pop();
            tempStack.push(item);
        }
        while (!tempStack.empty()) {
            T item = tempStack.pop();
            this.push(item);
        }
    }


    @Override
    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
            count--;
        }
        return result;
    }

    @Override
    public StackI<T> getAll(Predicate<? super T> predicate) {
        StackI<T> rest = new LinkedStack<>();
        StackI<T> result = new LinkedStack<>();
        while (!this.empty()) {
            T item = this.pop();
            if (predicate.apply(item)) {
                result.push(item);
            } else {
                rest.push(item);
            }
        }
        this.pushAll(reverse(rest));
        return reverse(result);
    }

    private StackI<T> reverse(StackI<T> stack) {
        StackI<T> resultStack = new LinkedStack<>();
        while (!stack.empty()) {
            T item = stack.pop();
            resultStack.push(item);
        }
        return resultStack;
    }

    private class Node<T> {
        T item;
        Node<T> next;

        private Node() {
            item = null;
            next = null;
        }

        private Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }
}
