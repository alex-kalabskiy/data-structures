package com.training.java.structures.stack;

import com.training.java.model.Animal;
import com.training.java.model.AnimalType;
import com.training.java.model.Bird;
import com.training.java.model.Mammal;
import com.training.java.service.Predicate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * Created by Alex on 22.03.15.
 */
public class LinkedStackTest {
    @Test
    public void push() throws Exception {
        StackI<Integer> numbers = new LinkedStack<>();
        numbers.push(1);
        numbers.push(2);
        numbers.push(3);

        assertTrue(!numbers.empty());
        assertTrue(numbers.size() == 3);
    }

    @Test
    public void pop() throws Exception {
        StackI<String> numbers = new LinkedStack<>();
        numbers.push("1");
        numbers.push("2");
        String item3 = "3";
        numbers.push(item3);
        numbers.push(null);

        String result = numbers.pop();

        assertEquals(result, item3);

        assertTrue(!numbers.empty());
        assertTrue(numbers.size() == 2);

    }

    @Test
    public void pushAll() throws Exception {
        StackI<String> numbers = new LinkedStack<>();
        numbers.push("1");
        numbers.push("2");
        numbers.push("3");
        numbers.push(null);
        int size1 = numbers.size();
        StackI<String> numbers2 = new LinkedStack<>();
        numbers2.pushAll(numbers);
        int size2 = numbers2.size();
        assertEquals(size2, size1);

        assertEquals(numbers2.pop(), "3");
    }

    @Test
    public void pushAllAnimals() throws Exception {
        StackI<Animal> animals = new LinkedStack<>();
        animals.push(new Bird("1", "@"));
        animals.push(new Mammal("01", "0@"));
        int size1 = animals.size();

        StackI<Animal> animal2 = new LinkedStack<>();
        animal2.pushAll(animals);
        int size2 = animal2.size();
        assertEquals(size2, size1);
    }

    @Test
    public void getAllWithPredicate() throws Exception {
        StackI<Integer> integerStackI = new LinkedStack<>();
        integerStackI.push(1);
        integerStackI.push(2);
        integerStackI.push(3);
        integerStackI.push(4);
        integerStackI.push(5);
        StackI<Integer> resultStack = integerStackI.getAll(new Predicate<Integer>(){

            @Override
            public boolean apply(Integer exp) {
                return exp.intValue() > 3;
            }
        });
        assertEquals(resultStack.size(), 2);
        assertTrue(resultStack.pop().intValue() > 3);
    }

    @Test
    public void putAllChild() throws Exception {
        StackI<Bird> birdStackI = new LinkedStack<>();
        birdStackI.push(new Bird("111", "222"));
        birdStackI.push(new Bird("333", "222"));
        StackI<Animal> animalStackI = new LinkedStack<>();
        animalStackI.pushAll(birdStackI);

        assertEquals(animalStackI.size(), 2);
        Animal animal = animalStackI.pop();
        assertEquals(animal.getType(), AnimalType.BIRD);

        assertEquals(animal.getName(), "333");
    }

    @Test
    public void getAllPredicate() throws Exception {
        StackI<Bird> birdStackI = new LinkedStack<>();
        birdStackI.push(new Bird("111", "1"));
        birdStackI.push(new Bird("333", "2"));
        birdStackI.push(new Bird("333", "3"));
        birdStackI.push(new Bird("444", "4"));
        StackI<Bird> resultStack = birdStackI.getAll(new Predicate<Animal>() {
            @Override
            public boolean apply(Animal exp) {
                return exp.getName().equals("333");
            }
        });

        assertEquals(resultStack.size(), 2);
        Bird bird = resultStack.pop();
        assertEquals(bird.getName(), "333");
        assertEquals(bird.getSpecies(), "3");
    }
}

