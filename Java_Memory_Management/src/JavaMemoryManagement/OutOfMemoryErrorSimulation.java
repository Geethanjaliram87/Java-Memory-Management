package JavaMemoryManagement;
import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorSimulation {

    public static void main(String[] args) {
        // List to hold large objects (simulating memory exhaustion)
        List<int[]> list = new ArrayList<>();
        
        try {
            // Infinite loop to keep adding large arrays to the list
            while (true) {
                // Allocate a large array (e.g., 1 million integers ~ 4MB)
                int[] largeArray = new int[1_000_000];

                // Add the large array to the list to hold a reference (prevent garbage collection)
                list.add(largeArray);

                // Print current memory status
                Runtime runtime = Runtime.getRuntime();
                long freeMemory = runtime.freeMemory();
                long totalMemory = runtime.totalMemory();
                long usedMemory = totalMemory - freeMemory;
                
                System.out.println("Used Memory: " + usedMemory + " bytes");
                System.out.println("Free Memory: " + freeMemory + " bytes");
                System.out.println("Total Memory: " + totalMemory + " bytes");
                
                // Adding a short sleep to slow down memory consumption (for visualization purposes)
                Thread.sleep(100);
            }
        } catch (OutOfMemoryError e) {
            // Catching OutOfMemoryError and handling it gracefully
            System.err.println("OutOfMemoryError caught: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
