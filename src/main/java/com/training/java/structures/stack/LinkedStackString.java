package com.training.java.structures.stack;

import com.training.java.service.Predicate;

/**
 * StackI implementation for String.
 *
 * @author Alex
 * @since 14.03.15.
 */
public class LinkedStackString implements StackI<String> {
    private Node top = new Node();
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
    public void push(String item) {
        top = new Node(item, top);
        count++;
    }

    //todo save order of stack
    @Override
    public void pushAll(StackI<? extends String> stack) {
        if (!stack.empty()) {
            do {
                String item = stack.pop();
                this.push(item);
            } while (!stack.empty());
        }

    }

    @Override
    public String pop() {
        String result = top.item;
        if (!top.end()) {
            top = top.next;
            count--;
        }
        return result;
    }

    @Override
    public StackI<String> getAll(Predicate<? super String> predicate) {
        StackI<String> rest = new LinkedStackString();
        StackI<String> result = new LinkedStackString();
        while (!this.empty()) {
            String item = this.pop();
            if (predicate.apply(item)) {
                result.push(item);
            } else {
                rest.push(item);
            }
        }
        this.pushAll(rest);
        return result;
    }

    private static class Node {
        String item;
        Node next;

        private Node() {
            item = null;
            next = null;
        }

        private Node(String item, Node next) {
            this.item = item;
            this.next = next;
        }

        boolean end() {
            return item == null && next == null;
        }
    }
}
