package com.training.java.model;

/**
 *
 * Created by Alex on 29.03.15.
 */
public class Mammal extends Animal{
    public final static String [] MAMMAL_SPECIES = new String[]{"cat", "dog", "cow"};

    public Mammal(String name, String species) {
        super(name, species);
    }

    public String nurse(){
        return "I can nurse";
    }

    @Override
    public String iCanDo() {
        return nurse();
    }

    @Override
    public AnimalType getType() {
        return AnimalType.MAMMAL;
    }
}
