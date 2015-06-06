package com.training.java.model;

import java.util.Random;

import static com.training.java.model.Bird.BIRD_SPECIES;
import static com.training.java.model.Mammal.MAMMAL_SPECIES;

/**
 *
 * Created by Alex on 29.03.15.
 */
public enum AnimalType {
    MAMMAL(MAMMAL_SPECIES),
    BIRD(BIRD_SPECIES);

    public final String[] species;
   

    AnimalType(String[] species) {
        this.species = species;
    }

    public String[] getSpecies() {
        return species;
    }

    public boolean isEqualType(Animal animal) {
        return this.equals(animal.getType());
    }

    public static AnimalType getRandomAnimalType() {
        int number = new Random().nextInt(AnimalType.values().length);


        return AnimalType.values()[number];
    }
}
