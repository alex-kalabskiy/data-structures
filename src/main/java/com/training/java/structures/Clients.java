package com.training.java.structures;

/**
 *
 * Created by Alex on 04.07.2015.
 */
public final class Clients {
    public static final String OPERATION_WRITE = "write";
    public static final String OPERATION_READ_BY_INDEX = "readByIndex";
    public static final String OPERATION_READ_BY_ITERATOR = "readByIterator";
    public static final String OPERATION_SORT = "sort";

    private Clients() {
    }
    public static void printResult(String listType, int amount, long startTime, String operation) {
        long finishTime = System.currentTimeMillis();
        double duration = (finishTime - startTime) * 1.0 / 1000;
        System.out.printf("%s ; %d ; %10.8f ; %s \n", listType, amount, duration, operation);
    }
}
