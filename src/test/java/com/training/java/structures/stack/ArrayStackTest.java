package com.training.java.structures.stack;

import com.training.java.model.Animal;
import com.training.java.model.Bird;
import com.training.java.model.Mammal;
import com.training.java.service.Predicate;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 *
 * Created by Alex on 10.04.15.
 */
public class ArrayStackTest {
    @Test
    public void createStack() throws Exception {
        StackI<Animal> animalStackI = new ArrayStack<>();
        assertTrue(animalStackI.empty());
        assertTrue(animalStackI.size() == 0);
    }

    @Test
    public void push() throws Exception {
        StackI<String> stackI = new ArrayStack<>();
        stackI.push("1");
        stackI.push("2");
        stackI.push("3");

        assertTrue(!stackI.empty());
        assertTrue(stackI.size() == 3);
    }

    @Test
    public void pop() throws Exception {
        StackI<String> stackI = new ArrayStack<>();
        stackI.push("1");
        stackI.push("2");
        String last = "3";
        stackI.push(last);
        String item = stackI.pop();

        assertEquals(item, last);
        assertTrue(!stackI.empty());
        assertTrue(stackI.size() == 2);
    }

    @Test
    public void pushAll() throws Exception {
        StackI<Animal> animals = new ArrayStack<>();
        Bird itemBird = new Bird("1", "@");
        animals.push(itemBird);
        Mammal itemMammal = new Mammal("01", "0@");
        animals.push(itemMammal);
        int size1 = animals.size();

        StackI<Animal> animal2 = new ArrayStack<>();
        animal2.pushAll(animals);
        int size2 = animal2.size();
        assertEquals(size2, size1);
        Animal animal = animal2.pop();
        assertEquals(animal, itemMammal);
    }
    @Test
    public void getAllPredicate() throws Exception {
        StackI<Bird> birdStackI = new ArrayStack<>();
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
        assertEquals(bird.getSpecies(),"3");
    }
}
