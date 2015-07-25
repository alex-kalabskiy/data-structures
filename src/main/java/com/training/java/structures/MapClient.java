package com.training.java.structures;

import java.util.HashMap;
import java.util.Map;


public class MapClient {

    public static void main(String[] args) {
        Map students = new HashMap();
        students.put("1", "Kalabskiy Alexander");
        students.put("2", "Mattiychuk Dmitriy");
        students.put("3", "Fathulin Igor");
        students.put("4", "Litvinenko Vitaliy");
        students.put("5", "Soktin Vladislav");
        students.put("6", "Chernenko Nataliya");
        System.out.println("Get value by key 1: " + students.get("1"));
        System.out.println();
        System.out.println("Get all keys: " + students.keySet());
        System.out.println();
        System.out.println("Get all values: " + students.values());
        System.out.println();
        System.out.println("Iterate through entries: " + students.entrySet());
        System.out.println();
        System.out.println("Check the key 6: " + students.containsKey("6"));
        System.out.println();
        students.remove("6");
        System.out.println("Check the values Chernenko Nataliya after remove: "
                + students.containsValue("Chernenko Nataliya"));
        System.out.println();
        System.out.println("Map is empty? : " + students.isEmpty());
        System.out.println();
        students.clear();
        System.out.println("Check the Map is empty after clear: " + students.isEmpty());
    }
}
