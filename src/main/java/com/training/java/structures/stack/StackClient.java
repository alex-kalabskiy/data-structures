package com.training.java.structures.stack;

import com.training.java.model.*;
import com.training.java.service.Predicate;
import com.training.java.structures.DataStructureClient;

import static com.training.java.model.Animal.ANIMAL_NAMES;
import static com.training.java.model.AnimalType.BIRD;
import static com.training.java.model.AnimalType.MAMMAL;

/**
 *
 *
 * Created by Alex on 14.03.15.
 */
public class StackClient implements DataStructureClient {

    public  final int startAmount;
    public  final int finishAmount;
    public  final int stepAmount;
    private AnimalFactory animalFactory = new AnimalFactory();
    private StackFactory<Animal> stackFactory = new StackFactory<>();

    public StackClient(int startAmount, int finishAmount, int stepAmount) {
        this.startAmount = startAmount;
        this.finishAmount = finishAmount;
        this.stepAmount = stepAmount;
    }


    @Override
    public void test()  {
        for (int amount = this.startAmount; amount <= finishAmount; amount*=stepAmount){
            testStackImplementation(amount, StackFactory.LINKED_STACK);
            testStackImplementation(amount, StackFactory.ARRAY_STACK);
            System.out.println("----------------------------------");
        }
    }

    private void testStackImplementation(int amountOfObjects, String stackImplementation) {
//        System.out.println("Testing stack implemetation: " + stackImplementation);
//        System.out.println("Creating stack");
        long startTime = System.currentTimeMillis();
        StackI<Animal> animalStack = getAnimalStack(amountOfObjects, stackImplementation);
//        System.out.println("Sorting started");
        animalStack = sortAnimals(animalStack, stackImplementation);
        long finishTime = System.currentTimeMillis();
        System.out.println(stackImplementation + ";" + amountOfObjects + ";" +
                ((finishTime - startTime) * 1.0 / 1000));
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
        String[] animalTypeSpecies = animalType.getSpecies();

        StackI<Animal> resultStack = stackFactory.createStack(stackImplementation, animalStack.size());
        for (String species : animalTypeSpecies) {
            StackI<Animal> sortedBySpeciesStack = getAllBySpecies(animalStack, species);
            sortedBySpeciesStack = sortAnimalsByNames(sortedBySpeciesStack, stackImplementation);
            resultStack.pushAll(sortedBySpeciesStack);
        }
        return resultStack;
    }

    private StackI<Animal> sortAnimalsByNames(StackI<Animal> animalStack, String stackImplementation) {
        StackI<Animal> resultStack = stackFactory.createStack(stackImplementation, animalStack.size());
        for (String ANIMAL_NAME : ANIMAL_NAMES) {
            StackI<Animal> sortedByNameStack = getAllByName(animalStack, ANIMAL_NAME);
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
