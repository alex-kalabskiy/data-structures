package com.training.java.service;

/**
 * Predicate for conditional expressions.
 *
 * @author  Alex
 * @since  04.04.15.
 */
public interface Predicate<E> {
    /**
     * Returns true, if expression applies condition,
     * false otherwise.
     *
     * @param exp Expression to check.
     * @return true, if expression applies condition, false otherwise.
     */
    boolean apply(E exp);

}
