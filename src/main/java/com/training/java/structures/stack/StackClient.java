package com.training.java.structures.stack;

import com.training.java.model.*;
import com.training.java.service.Predicate;

import static com.training.java.model.Animal.ANIMAL_NAMES;
import static com.training.java.model.AnimalType.BIRD;
import static com.training.java.model.AnimalType.MAMMAL;

/**
 *
 *
 * Created by Alex on 14.03.15.
 */
public class StackClient {

    public static final int AMOUNT = 100;
    private AnimalFactory animalFactory = new AnimalFactory();
    private StackFactory stackFactory = new StackFactory();

    public static void main(String[] args) {
        for (int amount = AMOUNT; amount <= 10000000; amount*=10){
            testStackImplementation(amount, StackFactory.LINKED_STACK);
            testStackImplementation(amount, StackFactory.ARRAY_STACK);
            System.out.println("----------------------------------");
        }
    }

    private static void testStackImplementation(int amountOfObjects, String stackImplementation) {
        StackClient client = new StackClient();
//        System.out.println("Testing stack implemetation: " + stackImplementation);
//        System.out.println("Creating stack");
        StackI<Animal> animalStack = client.getAnimalStack(amountOfObjects, stackImplementation);
//        System.out.println("Sorting started");
        long startTime = System.currentTimeMillis();
        animalStack = client.sortAnimals(animalStack, stackImplementation);
        long finishTime = System.currentTimeMillis();
        System.out.println("amount:" + amountOfObjects + ", implementation: " + stackImplementation +
                ", time:" + ((finishTime - startTime) * 1.0 / 1000) + " seconds");
//        client.showElements(animalStack);
    }

    private StackI<Animal> sortAnimals(StackI<Animal> animalStack, String stackImplementation) {
        StackI<Animal> resultStack = stackFactory.createStack(stackImplementation, animalStack.size());
        for (int i = 0; i < AnimalType.values().length; i++) {
            AnimalType animalType = AnimalType.values()[i];
            StackI<Animal> sortedByTypeStack = getAllByType(animalStack, animalType);
            sortedByTypeStack = sortAnimalsBySpecies(sortedByTypeStack, animalType, stackImplementation);
            resultStack.pushAll(sortedByTypeStack);
        }
        return resultStack;
    }

    private StackI<Animal> getAllByType(StackI<Animal> animalStack, final AnimalType animalType) {
        return animalStack.getAll(new Predicate<Animal>() {
            @Override
            public boolean apply(Animal exp) {
                return animalType.isEqualType(exp);
            }
        });
    }

    private StackI<Animal> sortAnimalsBySpecies(StackI<Animal> animalStack,
                                                AnimalType animalType,
                                                String stackImplementation) {
        String[] species = animalType.getSpecies();

        StackI<Animal> resultStack = stackFactory.createStack(stackImplementation, animalStack.size());
        for (int i = 0; i < species.length; i++) {
            StackI<Animal> sortedBySpeciesStack = getAllBySpecies(animalStack, species[i]);
            sortedBySpeciesStack = sortAnimalsByNames(sortedBySpeciesStack, stackImplementation);
            resultStack.pushAll(sortedBySpeciesStack);
        }
        return resultStack;
    }

    private StackI<Animal> sortAnimalsByNames(StackI<Animal> animalStack, String stackImplementation) {
        StackI<Animal> resultStack = stackFactory.createStack(stackImplementation, animalStack.size());
        for (int i = 0; i < ANIMAL_NAMES.length; i++) {
            StackI<Animal> sortedByNameStack = getAllByName(animalStack, ANIMAL_NAMES[i]);
            resultStack.pushAll(sortedByNameStack);
        }
        return resultStack;
    }

    private StackI<Animal> getAllByName(StackI<Animal> animalStack, final String name) {
        return animalStack.getAll(new Predicate<Animal>() {
            @Override
            public boolean apply(Animal exp) {
                return name.equals(exp.getName());
            }
        });
    }

    private StackI<Animal> getAllBySpecies(StackI<Animal> animalStack, final String species) {
        return animalStack.getAll(new Predicate<Animal>() {
            @Override
            public boolean apply(Animal exp) {
                return species.equals(exp.getSpecies());
            }
        });
    }

    private StackI<Animal> showElements(StackI<Animal> animalStack) {
        StackI<Animal> temporaryStack = new LinkedStack<>();
        while (!animalStack.empty()) {
            Animal animal = animalStack.pop();
            animal.whoAmI();
            temporaryStack.push(animal);
        }
        return temporaryStack;
    }

    private StackI<Animal> getAnimalStack(int numberOfAnimals, String stackImplementation) {
        StackI<Animal> animalStack = stackFactory.createStack(stackImplementation, numberOfAnimals);
        for (int a = 1; a <= numberOfAnimals; a++) {
            AnimalType animalType = resolveAnimalType(a);
            Animal animal = animalFactory.getAnimal(animalType);
            animalStack.push(animal);
        }
        return animalStack;
    }

    private AnimalType resolveAnimalType(int elementNumber) {
        if (elementNumber % 2 == 0) {
            return BIRD;
        }
        return MAMMAL;
    }
}