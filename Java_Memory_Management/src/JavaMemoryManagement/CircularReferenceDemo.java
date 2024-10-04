package JavaMemoryManagement;

class ClassA {
    ClassB classB;  // Reference to ClassB

    public ClassA() {
        System.out.println("ClassA object created.");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("ClassA object is being garbage collected.");
        super.finalize();
    }
}

class ClassB {
    ClassA classA;  // Reference to ClassA

    public ClassB() {
        System.out.println("ClassB object created.");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("ClassB object is being garbage collected.");
        super.finalize();
    }
}

public class CircularReferenceDemo {
    public static void main(String[] args) {
        // Create objects of ClassA and ClassB
        ClassA a = new ClassA();
        ClassB b = new ClassB();

        // Set circular references
        a.classB = b;
        b.classA = a;

        // Clear the strong references
        a = null;
        b = null;

        // Suggest garbage collection
        System.out.println("Suggesting Garbage Collection...");
        System.gc();

        // Run finalization
        System.runFinalization();

        // Wait for some time to observe the finalization output
        try {
            Thread.sleep(1000); // Give time for GC to process
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End of main method.");
    }
}

