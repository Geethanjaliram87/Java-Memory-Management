package JavaMemoryManagement;

public class ObjectFinalizationDemo {

    // Custom class that overrides finalize()
    static class FinalizableObject {

        // Overriding finalize method to print a message
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Object is being garbage collected: " + this);
        }
    }

    public static void main(String[] args) {
        // Create multiple instances of FinalizableObject
        FinalizableObject obj1 = new FinalizableObject();
        FinalizableObject obj2 = new FinalizableObject();
        FinalizableObject obj3 = new FinalizableObject();

        // Setting objects to null, making them eligible for garbage collection
        obj1 = null;
        obj2 = null;
        obj3 = null;

        // Suggest garbage collection
        System.out.println("Calling System.gc() to suggest garbage collection...");
        System.gc();

        // Force finalization
        System.out.println("Calling System.runFinalization() to force finalization...");
        System.runFinalization();

        // Giving some time for GC to happen
        try {
            Thread.sleep(1000);  // Pause for GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method.");
    }
}

