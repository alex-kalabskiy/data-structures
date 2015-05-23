package com.training.java.model;

import com.training.java.model.Animal;
import com.training.java.model.AnimalType;
import com.training.java.model.Bird;
import com.training.java.model.Mammal;

import java.util.Random;

import static com.training.java.model.Animal.ANIMAL_NAMES;
import static com.training.java.model.AnimalType.BIRD;
import static com.training.java.model.AnimalType.MAMMAL;

/**
 * Creates animals.
 *
 * Created by Alex on 29.03.15.
 */
public class AnimalFactory {
    private final Random random = new Random();

    public Animal getAnimal(AnimalType animalType) {
        Animal animal;

        switch (animalType) {
            case BIRD:
                animal = getBird();
                break;
            case MAMMAL:
                animal = getMammal();
                break;
            default:
                throw new IllegalArgumentException("Unknown animal type");
        }
        return animal;
    }

    public Mammal getMammal() {
        String name = getRandomElement(ANIMAL_NAMES);
        String species = getRandomElement(MAMMAL.getSpecies());
        return new Mammal(name, species);
    }

    public Bird getBird() {
        String name = getRandomElement(ANIMAL_NAMES);
        String species = getRandomElement(BIRD.getSpecies());
        return new Bird(name, species);
    }

    private String getRandomElement(final String[] names) {
        int n = random.nextInt(names.length);
        return names[n];
    }
}
