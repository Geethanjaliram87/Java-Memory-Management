package JavaMemoryManagement;

public class HeapVsStackMemoryDemo {
    
    // A large array that will be allocated on the heap
    private static class LargeObject {
        private int[] data;

        public LargeObject(int size) {
            data = new int[size]; // Allocate a large array on the heap
        }
    }

    public static void main(String[] args) {
        // Create small primitive variables in the stack
        int smallInt = 42;
        double smallDouble = 3.14;

        System.out.println("Small primitive variables:");
        System.out.println("Integer: " + smallInt);
        System.out.println("Double: " + smallDouble);

        // Create a large object in the heap
        LargeObject largeObject1 = new LargeObject(10_000_000); // Allocate large object on the heap
        System.out.println("Created large object 1 in heap.");

        // Create another large object in the heap
        LargeObject largeObject2 = new LargeObject(20_000_000); // Allocate another large object on the heap
        System.out.println("Created large object 2 in heap.");

        // Set large objects to null to make them eligible for garbage collection
        largeObject1 = null;
        largeObject2 = null;

        // Suggest garbage collection
        System.out.println("Suggesting garbage collection...");
        System.gc();

        // Pause to allow GC to complete
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method.");
    }
}

