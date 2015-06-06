package com.training.java.structures.queue;

import com.training.java.model.Animal;
import com.training.java.model.AnimalFactory;
import com.training.java.model.AnimalType;
import com.training.java.structures.DataStructureClient;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * Created by Alex on 23.05.2015.
 */
public class QueueClient implements DataStructureClient{
    private final int startAmount;
    private final int finishAmount;
    private final int stepAmount;
    private AnimalFactory animalFactory = new AnimalFactory();

    public QueueClient(int startAmount, int finishAmount, int stepAmount) {
        this.startAmount = startAmount;
        this.finishAmount = finishAmount;
        this.stepAmount = stepAmount;
    }

    @Override
    public void test() {
        for (int amount = startAmount;  amount <= finishAmount; amount*=stepAmount) {
            testPerformanceForAmount(amount);

        }
    }

    private void testPerformanceForAmount(int amount) {
        long startTime = System.currentTimeMillis();
        Queue<Animal> queue = createQueue(amount);
        long finishTime = System.currentTimeMillis();
        System.out.println(queue.getClass().getName() + ";" + amount + ";" +
                ((finishTime - startTime) * 1.0 / 1000));

        //        client.showElements(queue);
    }

    private void showElements(Queue<Animal> queue) {
        if (queue == null) {
            return;
        }
        for (;!queue.isEmpty(); ) {
            queue.poll().whoAmI();
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
