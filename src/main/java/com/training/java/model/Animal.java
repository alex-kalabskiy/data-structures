package com.training.java.model;

/**
 *
 * Created by Alex on 29.03.15.
 */
public abstract class Animal implements Comparable<Animal> {
    public final static String [] ANIMAL_NAMES = new String[]{"Masha", "Pavel", "Ola"};
    final String name;

    final String species;

    public Animal(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    protected abstract String iCanDo();

    public abstract AnimalType getType();

    public void whoAmI(){
        System.out.println("I am " + getSpecies() + ", my name is " + getName() + ", " + iCanDo());
    }

    @Override
    public int compareTo(Animal o) {
        if (!this.getType().equals(o.getType())) {
            return this.getType().compareTo(o.getType());
        } else if (!this.getSpecies().equals(o.getSpecies())) {
            return this.getSpecies().compareTo(o.getSpecies());
        } else {
            return this.getName().compareTo(o.getName());
        }
    }
}
