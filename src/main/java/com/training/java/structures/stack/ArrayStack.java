package com.training.java.structures.stack;

import com.training.java.service.Predicate;

import java.util.Arrays;

/**
 *
 *
 * Created by Alex on 10.04.15.
 */
public class ArrayStack<T> implements StackI<T> {

    private final int DEFAULT_SIZE = 2;
    public final int LENGTH_FACTOR = 2;
    private final double MAX_CAPACITY_RATE = 0.75;
    private final double MIN_CAPACITY_RATE = 0.25;

    private Object[] array = new Object[DEFAULT_SIZE];
    private int topIndex = -1; //empty stack

    public ArrayStack() {
    }

    public ArrayStack(int capacity) {
        this.array = new Object[capacity];
    }

    @Override
    public boolean empty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public void push(T item) {
        if (item != null){
            ensureMaxCapacity();
            array[++topIndex] = item;
        }
    }

    private void ensureMaxCapacity() {
        double capacityRate = size() * 1.0 / array.length;
        if (capacityRate > MAX_CAPACITY_RATE) {
            increaseCapacity();
        }
//        else if (capacityRate < MIN_CAPACITY_RATE) {
//            decreaseCapacity();
//        }
    }

    private void increaseCapacity() {
        T[] increasedArray = (T[]) Arrays.copyOf(array, array.length * LENGTH_FACTOR);
        array = increasedArray;
//        Object[] newArray = new Object[array.length * LENGTH_FACTOR];
//        replaceArray(newArray);
    }

//    private void replaceArray(Object[] newArray) {
//        for (int i = 0; i <= topIndex; i++) {
//            newArray[i] = array[i];
//        }
//        array = newArray;
//    }

    @Override
    public T pop() {
        if (empty()) {
            return null;
        } else {
            T item = (T) array[topIndex--];
            ensureMinCapacity();
            return item;
        }
    }

    private void ensureMinCapacity() {
        double capacityRate = size() * 1.0 / array.length;
//        if (capacityRate > MAX_CAPACITY_RATE) {
//            increaseCapacity();
//        }
//        else
        if (capacityRate < MIN_CAPACITY_RATE) {
            decreaseCapacity();
        }
    }

    private void decreaseCapacity() {
        T[] decreasedArray = (T[]) Arrays.copyOf(array, array.length / LENGTH_FACTOR);
        array = decreasedArray;
//        if (array.length > DEFAULT_SIZE) {
//            Object[] newArray = new Object[array.length / LENGTH_FACTOR];
//            replaceArray(newArray);
//        }
    }

    @Override
    public void pushAll(StackI<? extends T> stack) {
        StackI<T> tempStack = new ArrayStack<>(stack.size());
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
    public StackI<T> getAll(Predicate<? super T> predicate) {
        StackI<T> rest = new ArrayStack<>();
        StackI<T> result = new ArrayStack<>();
        while (!this.empty()) {
            T item = this.pop();
            if (predicate.apply(item)) {
                result.push(item);
            } else {
                rest.push(item);
            }
        }
        this.pushAll(reverseStack(rest));
        return reverseStack(result);
    }

    private StackI<T> reverseStack(StackI<T> stack) {
        StackI<T> tempStack = new ArrayStack<>(stack.size());
        while (!stack.empty()) {
            T item = stack.pop();
            tempStack.push(item);
        }
        return tempStack;
    }
}
