package JavaMemoryManagement;

import java.util.Arrays;

public class LargeArrayMemoryDemo {

    public static void main(String[] args) {
        // Get the runtime reference to measure memory usage
        Runtime runtime = Runtime.getRuntime();

        // Measure initial memory usage
        System.out.println("Memory before array allocation:");
        printMemoryUsage(runtime);

        // Allocate a large array of integers
        int arraySize = 100_000_000;  // 100 million elements
        int[] largeArray = new int[arraySize];

        // Fill the array with some values
        for (int i = 0; i < arraySize; i++) {
            largeArray[i] = i % 100;  // Modulo to simulate data
        }

        // Measure memory usage after array allocation
        System.out.println("Memory after array allocation:");
        printMemoryUsage(runtime);

        // Process the array (e.g., summing the elements)
        long sum = Arrays.stream(largeArray).sum();
        System.out.println("Sum of array elements: " + sum);

        // Optimize memory usage: Clear the array after processing
        largeArray = null;  // Set array to null, making it eligible for garbage collection

        // Suggest garbage collection
        System.gc();
        System.out.println("Memory after clearing the array and running GC:");
        printMemoryUsage(runtime);
    }

    // Method to print the current memory usage
    private static void printMemoryUsage(Runtime runtime) {
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("Total Memory: " + totalMemory + " bytes");
        System.out.println("Free Memory: " + freeMemory + " bytes");
        System.out.println("Used Memory: " + usedMemory + " bytes");
        System.out.println("-----------------------------------------");
    }
}

