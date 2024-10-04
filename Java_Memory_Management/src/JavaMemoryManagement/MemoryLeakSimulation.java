package JavaMemoryManagement;
import java.util.HashMap;
import java.util.Map;

public class MemoryLeakSimulation {

    // Static Map simulating a memory leak
    private static Map<Integer, String> cache = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            // Add objects to the cache (simulating an object cache that isn't cleaned up)
            cache.put(i, new String("Data " + i));

            // Print memory usage every 100,000 iterations
            if (i % 100000 == 0) {
                printMemoryStatus();
            }
        }
        
        // After adding objects, print final memory status
        System.out.println("Memory status after loop:");
        printMemoryStatus();
        
        // Optional: Comment out the cache clear to simulate memory leak
        // Clear cache to release object references and fix the memory leak
        // cache.clear();
        
        System.out.println("Memory status after clearing cache:");
        printMemoryStatus();
    }

    // Utility method to print memory status
    private static void printMemoryStatus() {
        Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Used Memory: " + usedMemory + " bytes");
        System.out.println("Free Memory: " + freeMemory + " bytes");
        System.out.println("Total Memory: " + totalMemory + " bytes");
        System.out.println("--------------------------------------");
    }
}
