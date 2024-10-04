package JavaMemoryManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalMemoryManagementDemo {
    
    // Create a ThreadLocal variable to store per-thread data
    private static ThreadLocal<String> threadLocalData = ThreadLocal.withInitial(() -> "Initial Value");

    public static void main(String[] args) throws InterruptedException {
        // Create a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Submit tasks to the executor service
        for (int i = 0; i < 10; i++) {
            final int threadNumber = i;
            executorService.submit(() -> {
                // Set ThreadLocal data
                threadLocalData.set("Thread " + threadNumber + " data");

                // Simulate work
                try {
                    Thread.sleep(2000); // Simulate processing for 2 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                // Print the ThreadLocal data
                System.out.println(Thread.currentThread().getName() + ": " + threadLocalData.get());
                
                // Clear ThreadLocal data
                threadLocalData.remove();
            });
        }

        // Shutdown the executor service gracefully
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        
        // Suggest garbage collection
        System.out.println("Suggesting garbage collection...");
        System.gc();

        // Pause to allow GC to complete
        Thread.sleep(1000);
        
        System.out.println("End of main method.");
    }
}
