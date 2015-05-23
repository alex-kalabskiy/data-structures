package com.training.java.model;

/**
 *
 * Created by Alex on 29.03.15.
 */
public class Bird extends Animal {
    public final static String [] BIRD_SPECIES = new String[]{"parrot", "eagle", "hawk"};

    public Bird(String name, String species) {
        super(name, species);
    }

    public String fly() {
        return "I can fly";
    }

    @Override
    public String iCanDo() {
        return fly();
    }

    @Override
    public AnimalType getType() {
        return AnimalType.BIRD;
    }
}
