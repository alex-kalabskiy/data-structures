package com.training.java.structures;

import com.training.java.structures.list.ListClient;
import com.training.java.structures.queue.QueueClient;
import com.training.java.structures.set.SetClient;
import com.training.java.structures.stack.LinkedStack;
import com.training.java.structures.stack.StackClient;
import com.training.java.structures.stack.StackI;

/**
 *
 * Created by Alex on 06.06.2015.
 */

public class ClientTester {
    public static final int START_AMOUNT = 100;
    public static final int FINISH_AMOUNT = 100000;
    public static final int STEP_AMOUNT = 10;

    private StackI<DataStructureClient> dataStructureClientStack = new LinkedStack<>();

    public static void main(String[] args) {

        ClientTester tester = new ClientTesterBuilder()
                .withDataStructureClient(new StackClient(START_AMOUNT, FINISH_AMOUNT, STEP_AMOUNT))
                .withDataStructureClient(new QueueClient(START_AMOUNT, FINISH_AMOUNT, STEP_AMOUNT))
                .withDataStructureClient(new ListClient(START_AMOUNT, FINISH_AMOUNT, STEP_AMOUNT))
                .withDataStructureClient(new SetClient(START_AMOUNT, FINISH_AMOUNT, STEP_AMOUNT))
                .build();

        System.out.println("Data Structure; Amount; Duration; Operation");

        tester.testDataStructureClients();
    }

    private void testDataStructureClients() {
        while (!dataStructureClientStack.empty()) {
            DataStructureClient client = dataStructureClientStack.pop();
            System.out.println(client.getClass().getName());
            client.test();
        }
    }

    public StackI<DataStructureClient> getDataStructureClientStack() {
        return dataStructureClientStack;
    }

    private static class ClientTesterBuilder {
        ClientTester tester;

        public ClientTesterBuilder() {
            this.tester = new ClientTester();
        }

        ClientTesterBuilder withDataStructureClient(DataStructureClient client) {
            tester.getDataStructureClientStack().push(client);
            return this;
        }

        ClientTester build() {
            return tester;
        }
    }
}
