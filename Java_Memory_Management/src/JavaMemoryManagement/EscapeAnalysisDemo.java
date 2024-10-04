package JavaMemoryManagement;

import java.util.ArrayList;
import java.util.List;

public class EscapeAnalysisDemo {

    public static void main(String[] args) {
        // Measure memory before calling the method
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        // Measure execution time
        long startTime = System.nanoTime();
        
        // Call the method that uses local objects
        createLocalObjects(1_000_000);  // Create a large number of objects
        
        // Measure execution time after the method
        long endTime = System.nanoTime();
        
        // Measure memory after calling the method
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        // Print the results
        System.out.println("Memory used: " + (afterMemory - beforeMemory) + " bytes");
        System.out.println("Execution time: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private static void createLocalObjects(int numObjects) {
        List<LocalObject> objectList = new ArrayList<>();
        for (int i = 0; i < numObjects; i++) {
            // Creating local objects
            LocalObject obj = new LocalObject(i);
            objectList.add(obj); // Preventing escape from the method scope
        }
        // Optional: Clear the list to allow objects to be eligible for GC
        objectList.clear();
    }

    // Local object class
    static class LocalObject {
        private int value;

        public LocalObject(int value) {
            this.value = value;
        }

        // Getters and setters
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
