package com.training.java.structures.stack;

import com.training.java.service.Predicate;
import org.testng.annotations.Test;

/*import java.util.Stack;
*/

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test LinkedStack for String
 *
 * @author Alex
 * @since 14.03.15.
 */
public class LinkedStackStringTest {
    @Test
    public void creation() {
        StackI<String> stackString = new LinkedStackString();

        assertTrue(stackString.empty());
        assertTrue(stackString.size() == 0);
    }

    @Test
    public void push() throws Exception {
        StackI<String> stackString = new LinkedStackString();
        stackString.push("1");
        stackString.push("2");

        assertTrue(!stackString.empty());
        assertTrue(stackString.size() == 2);
    }

    @Test
    public void pop() throws Exception {
        StackI<String> stackString = new LinkedStackString();
        String item1 = "1";
        stackString.push(item1);
        String item2 = "2";
        stackString.push(item2);
        String result = stackString.pop();
        assertEquals(result, item2);
        assertTrue(!stackString.empty());
        assertTrue(stackString.size() == 1);
    }

    @Test
    public void pushAll() throws Exception {
        StackI<String> stackFirst = new LinkedStackString();
        stackFirst.push("1");
        stackFirst.push("2");
        int size1 = stackFirst.size();
        StackI<String> stackSecond = new LinkedStackString();
        stackSecond.pushAll(stackFirst);
        int size2 = stackSecond.size();
        assertTrue(size1 == size2);
    }

    @Test
    public void getAll() throws Exception {
        StackI<String> firstStackI = new LinkedStackString();
        firstStackI.push("abcde");
        firstStackI.push("bcdef");
        firstStackI.push("cdefg");
        firstStackI.push("defgh");
        firstStackI.push("efghi");
        firstStackI.push("fghij");
        StackI<String> resultStackI = firstStackI.getAll(new Predicate<String>() {

            @Override
            public boolean apply(String exp) {
                return exp.contains("ef");
            }
        });
        assertTrue(resultStackI.size() == 4);
    }
}
