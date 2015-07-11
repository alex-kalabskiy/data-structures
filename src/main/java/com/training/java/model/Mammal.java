package com.training.java.model;

/**
 *
 * Created by Alex on 29.03.15.
 */
public class Mammal extends Animal{
    public final static String [] MAMMAL_SPECIES = new String[]{"cat", "dog", "cow"};

    private final AnimalType type = AnimalType.MAMMAL;

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

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        return o instanceof Mammal;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
