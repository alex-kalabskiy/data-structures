package com.training.java.structures.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * Created by Alex on 20.06.2015.
 */
public class ListFactory<T> {

    public static final String LINKED_LIST = "linked";
    public static final String ARRAY_LIST = "array";
    public static final String VECTOR_LIST = "vector";

    public <T> List<T> createList(String listType) {
        if (LINKED_LIST.equals(listType)) {
            return new LinkedList<>();
        } else if (ARRAY_LIST.equals(listType)) {
            return new ArrayList<>();
        } else if (VECTOR_LIST.equals(listType)) {
            return new Vector<>();
        } else {
            throw new IllegalArgumentException("Not supported list type: " + listType);
        }

    }
}
