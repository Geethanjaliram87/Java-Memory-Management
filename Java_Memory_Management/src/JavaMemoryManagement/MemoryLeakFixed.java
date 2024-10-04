package JavaMemoryManagement;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class MemoryLeakFixed {

    // WeakHashMap allows garbage collection to reclaim memory when keys are no longer referenced
    private static Map<Integer, WeakReference<String>> cache = new WeakHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            // Use WeakReference to prevent memory leak
            cache.put(i, new WeakReference<>(new String("Data " + i)));

            // Print memory usage every 100,000 iterations
            if (i % 100000 == 0) {
                printMemoryStatus();
            }
        }
        
        // After adding objects, print final memory status
        System.out.println("Memory status after loop:");
        printMemoryStatus();

        // WeakReferences will be automatically cleaned up by GC
        System.gc();  // Request garbage collection to clean up unused weak references

        System.out.println("Memory status after suggesting GC:");
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
