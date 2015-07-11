package com.training.java.structures.list;

import com.training.java.model.Animal;
import com.training.java.model.AnimalFactory;
import com.training.java.structures.Clients;
import com.training.java.structures.DataStructureClient;

import java.util.Collections;
import java.util.List;

import static com.training.java.structures.Clients.*;

/**
 *
 * Created by Alex on 13.06.2015.
 */
public class ListClient implements DataStructureClient {

    private final int startAmount;
    private final int finishAmount;
    private final int stepAmount;
    private AnimalFactory animalFactory = new AnimalFactory();
    private ListFactory<Animal> listFactory = new ListFactory<>();

    public ListClient(int startAmount, int finishAmount, int stepAmount) {
        this.startAmount = startAmount;
        this.finishAmount = finishAmount;
        this.stepAmount = stepAmount;
    }

    @Override
    public void test() {

        for (int amount = startAmount; amount <= finishAmount; amount *= stepAmount) {
            testPerformanceForAmountAndImplementation(amount, ListFactory.ARRAY_LIST);
            testPerformanceForAmountAndImplementation(amount, ListFactory.LINKED_LIST);
            testPerformanceForAmountAndImplementation(amount, ListFactory.VECTOR_LIST);
        }
    }

    private void testPerformanceForAmountAndImplementation(int amount, String listType) {

        List<Animal> list = listFactory.createList(listType);

        testWrite(amount, listType, list);
        readByIndex(amount, listType, list);
        readByIterator(amount, listType, list);
        sortList(amount, listType, list);
    }

    private void sortList(int amount, String listType, List<Animal> list) {
        assert (list.size() == amount);
        long startTime = System.currentTimeMillis();
        Collections.sort(list);
        printResult(listType, amount, startTime, OPERATION_SORT);
    }

    private void readByIterator(int amount, String listType, List<Animal> list) {
        assert (list.size() == amount);
        long startTime = System.currentTimeMillis();
        for (Animal animal : list) {
            checkAnimal(animal);
        }
        printResult(listType, amount, startTime, OPERATION_READ_BY_ITERATOR);
    }

    private void readByIndex(int amount, String listType, List<Animal> list) {
        assert (list.size() == amount);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            checkAnimal(list.get(i));
        }
        printResult(listType, amount, startTime, OPERATION_READ_BY_INDEX);

    }

    private void checkAnimal(Animal animal) {
        assert (animal.getName() != null);
    }

    private void testWrite(int amount, String listType, List<Animal> list) {
        assert (list.isEmpty());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            Animal animal = animalFactory.getAnimal();
            list.add(animal);
        }
        printResult(listType, amount, startTime, OPERATION_WRITE);
    }
}
