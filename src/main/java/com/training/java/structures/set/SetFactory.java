package com.training.java.structures.set;

import java.util.*;

/**
 *
 * Created by Alex on 04.07.2015.
 */

public class SetFactory<T> {

    public static final String HASH_SET = "hash";
    public static final String TREE_SET = "tree";
    public static final String LINKED_HASH_SET = "linked hash set";

    public Set<T> createSet(String setType) {
        if (HASH_SET.equals(setType)) {
            return new HashSet<>();
        } else if (TREE_SET.equals(setType)) {
            return new TreeSet<>();
        } else if (LINKED_HASH_SET.equals(setType)) {
            return new LinkedHashSet<>();
        } else {
            throw new IllegalArgumentException("Not supported set type: " + setType);
        }

    }



}
