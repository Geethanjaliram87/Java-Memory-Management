package JavaMemoryManagement;

import java.util.HashSet;

public class StringMemoryFootprint {
    public static void main(String[] args) {
        // Number of String objects to create
        int numStrings = 1_000_000;
        // String value to duplicate
        String duplicateString = "Hello, Java!";

        // Creating a set to hold unique interned strings
        HashSet<String> internedStrings = new HashSet<>();

        // Measure memory usage before interning
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory usage before interning: " + memoryBefore + " bytes");

        // Creating a large number of String objects with many duplicates
        for (int i = 0; i < numStrings; i++) {
            String str = (i % 10 == 0) ? duplicateString : "RandomString" + i; // Introduce duplicates
            // Intern the string to reduce memory usage
            internedStrings.add(str.intern());
        }

        // Measure memory usage after interning
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory usage after interning: " + memoryAfter + " bytes");

        // Display the difference in memory usage
        long memoryUsageDifference = memoryAfter - memoryBefore;
        System.out.println("Memory usage difference: " + memoryUsageDifference + " bytes");

        // Displaying the count of unique interned strings
        System.out.println("Unique interned strings count: " + internedStrings.size());
    }
}

