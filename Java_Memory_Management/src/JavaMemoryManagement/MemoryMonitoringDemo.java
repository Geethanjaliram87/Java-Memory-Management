package JavaMemoryManagement;

import java.util.ArrayList;
import java.util.List;

public class MemoryMonitoringDemo {
    
    private static final int OBJECT_COUNT = 10_000_000; // Number of objects to create
    private static final int OBJECT_SIZE = 100; // Size of each object in bytes

    // Custom object class
    private static class MemoryHog {
        byte[] data = new byte[OBJECT_SIZE]; // Simulating memory usage
    }

    public static void main(String[] args) {
        List<MemoryHog> memoryHogs = new ArrayList<>();

        // Simulating heavy memory usage
        System.out.println("Starting to allocate memory...");
        for (int i = 0; i < OBJECT_COUNT; i++) {
            memoryHogs.add(new MemoryHog()); // Creating a new MemoryHog object
            if (i % 1_000_000 == 0) {
                System.out.println("Allocated " + (i + 1) + " objects");
            }
        }

        // Holding memory to simulate a memory-heavy process
        System.out.println("Memory allocation complete. Holding memory...");
        try {
            Thread.sleep(10000); // Holding for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clean up
        memoryHogs.clear(); // Clear the references to allow GC
        System.out.println("Cleared references. Suggesting garbage collection...");
        System.gc();

        // Pause to allow GC to happen
        try {
            Thread.sleep(2000); // Pause for GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of memory monitoring demo.");
    }
}
