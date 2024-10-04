	package JavaMemoryManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GCTuningDemo {

    static class Task implements Runnable {
        @Override
        public void run() {
            // Rapidly create and destroy objects
            for (int i = 0; i < 100_000; i++) {
                // Creating a dummy object
                Object obj = new Object();
            }
        }
    }

    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors(); // Get the number of available processors
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        long startTime = System.currentTimeMillis();

        // Submit tasks to the executor
        for (int i = 0; i < numThreads; i++) {
            executor.submit(new Task());
        }

        executor.shutdown(); // Shut down the executor
        while (!executor.isTerminated()) {
            // Wait for all tasks to finish
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken: " + (endTime - startTime) + " ms");
    }
}
