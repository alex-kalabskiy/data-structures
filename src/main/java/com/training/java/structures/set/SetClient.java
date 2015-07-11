package com.training.java.structures.set;

import com.training.java.model.Animal;
import com.training.java.model.AnimalFactory;
import com.training.java.structures.DataStructureClient;
import java.util.Set;
import static com.training.java.structures.Clients.*;
import static com.training.java.structures.Clients.OPERATION_WRITE;
import static com.training.java.structures.Clients.printResult;

/**
 * @author Alex
 * @since 29.06.15.
 */

public class SetClient implements DataStructureClient {

    private final int startAmount;
    private final int finishAmount;
    private final int stepAmount;
    private AnimalFactory animalFactory = new AnimalFactory();
    private SetFactory<Animal> setFactory = new SetFactory<>();

    public SetClient(int startAmount, int finishAmount, int stepAmount) {
        this.startAmount = startAmount;
        this.finishAmount = finishAmount;
        this.stepAmount = stepAmount;
    }

    @Override
    public void test() {

        for (int amount = startAmount; amount <= finishAmount; amount *= stepAmount) {
            testPerformanceForAmountAndImplementation(amount, SetFactory.HASH_SET);
            testPerformanceForAmountAndImplementation(amount, SetFactory.TREE_SET);
            testPerformanceForAmountAndImplementation(amount, SetFactory.LINKED_HASH_SET);
        }
    }

    private void testPerformanceForAmountAndImplementation(int amount, String setType) {

        Set<Animal> set = setFactory.createSet(setType);

        testWrite(amount, setType, set);
        readByIterator(amount, setType, set);
    }

    private void readByIterator(int amount, String setType, Set<Animal> set) {
        assert (!set.isEmpty());
        long startTime = System.currentTimeMillis();
        for (Animal animal : set) {
            checkAnimal(animal);
        }
        printResult(setType, amount, startTime, OPERATION_READ_BY_ITERATOR);
    }

    private void checkAnimal(Animal animal) {
        assert (animal.getName() != null);
    }

    private void testWrite(int amount, String setType, Set<Animal> set) {
        assert (set.isEmpty());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            Animal animal = animalFactory.getAnimal();
            set.add(animal);
        }
        printResult(setType, amount, startTime, OPERATION_WRITE);
    }
}
