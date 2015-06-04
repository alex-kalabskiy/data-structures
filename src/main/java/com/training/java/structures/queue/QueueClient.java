package com.training.java.structures.queue;

import com.training.java.model.Animal;
import com.training.java.model.AnimalFactory;
import com.training.java.model.AnimalType;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Created by Alex on 23.05.2015.
 */
public class QueueClient {
    private static final int START_AMOUNT = 100;
    private AnimalFactory animalFactory = new AnimalFactory();

    public static void main(String[] args) {
        for (int amount = START_AMOUNT;  amount <= 100; amount*=10) {
            testPerformanceForAmount(amount);
        }

    }

    private static void testPerformanceForAmount(int amount) {
        QueueClient client = new QueueClient();
        long startTime = System.currentTimeMillis();
        Queue<Animal> queue = client.createQueue(amount);
        long finishTime = System.currentTimeMillis();
        System.out.println("amount:" + amount + ", time:" + ((finishTime - startTime) * 1.0 / 1000) + " seconds");
        client.showElements(queue);
    }

    private void showElements(Queue<Animal> queue) {
        for (Animal animal : queue) {
            animal.whoAmI();
        }
    }

    private Queue<Animal> createQueue(int amount) {
        Queue<Animal> queue = new PriorityQueue<>(amount);
        for (int i = 1; i <= amount; i++) {
            AnimalType animalType = AnimalType.getRandomAnimalType();
            Animal animal = animalFactory.getAnimal(animalType);
            queue.offer(animal);
        }
        return queue;
    }

}
