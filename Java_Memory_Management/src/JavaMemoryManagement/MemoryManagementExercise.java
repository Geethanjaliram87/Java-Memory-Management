package JavaMemoryManagement;
public class MemoryManagementExercise {

    public static void main(String[] args) {
        // Get the runtime reference from the system
        Runtime runtime = Runtime.getRuntime();

        // Initial memory information
        System.out.println("Initial Memory Status:");
        printMemoryStatus(runtime);

        // Number of iterations (creating 10 million objects)
        int numberOfIterations = 10_000_000;

        // Loop to create objects and observe memory behavior
        for (int i = 0; i < numberOfIterations; i++) {
            // Creating millions of objects
            //Object obj = new Object();

            // Optionally clear references to make objects eligible for garbage collection
           // obj = null;  // Making the object eligible for GC

            // Print memory information every 1 million iterations to reduce output clutter
            if (i % 1_000_000 == 0) {
                System.out.println("Iteration: " + i);
                printMemoryStatus(runtime);

                // Suggest garbage collection every 2 million iterations
                if (i % 2_000_000 == 0) {
                    System.out.println("Suggesting Garbage Collection...");
                    System.gc();
                    // Pause to give time for garbage collection
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Memory status after GC:");
                    printMemoryStatus(runtime);
                    System.out.println("--------------------------------");
                }
            }
        }

        // Final memory status after all iterations
        System.out.println("Final Memory Status:");
        printMemoryStatus(runtime);
    }

    // Method to print the current memory status
    private static void printMemoryStatus(Runtime runtime) {
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.println("Total Memory: " + totalMemory + " bytes");
        System.out.println("Free Memory: " + freeMemory + " bytes");
        System.out.println("Used Memory: " + usedMemory + " bytes");
    }
}
