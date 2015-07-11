package com.training.java.model;

/**
 *
 * Created by Alex on 29.03.15.
 */
public class Bird extends Animal {
    public final static String [] BIRD_SPECIES = new String[]{"parrot", "eagle", "hawk"};

    private final AnimalType type = AnimalType.BIRD;

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
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        return o instanceof Bird;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
