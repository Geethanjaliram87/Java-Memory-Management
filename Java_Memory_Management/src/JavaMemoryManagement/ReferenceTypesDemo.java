package JavaMemoryManagement;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceTypesDemo {

    public static void main(String[] args) {
        // Strong reference
        Object strongReference = new Object();
        System.out.println("Strong Reference: " + strongReference);

        // Weak reference
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
        System.out.println("Weak Reference (before GC): " + weakReference.get());

        // Soft reference
        SoftReference<Object> softReference = new SoftReference<>(new Object());
        System.out.println("Soft Reference (before GC): " + softReference.get());

        // Phantom reference
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(new Object(), referenceQueue);
        System.out.println("Phantom Reference (before GC): " + phantomReference.get());

        // Suggest garbage collection
        System.out.println("Suggesting Garbage Collection...");
        System.gc();

        // Check references after GC
        System.out.println("Weak Reference (after GC): " + weakReference.get());
        System.out.println("Soft Reference (after GC): " + softReference.get());
        System.out.println("Phantom Reference (after GC): " + phantomReference.get());

        // Checking if the Phantom Reference has been enqueued
        if (phantomReference.isEnqueued()) {
            System.out.println("Phantom reference is enqueued.");
        } else {
            System.out.println("Phantom reference is not enqueued.");
        }

        // Simulate the scenario where the soft reference may still exist
        System.out.println("Clearing strong reference...");
        strongReference = null;

        // Suggest garbage collection again
        System.out.println("Suggesting Garbage Collection again...");
        System.gc();

        // Check references after GC
        System.out.println("Weak Reference (after second GC): " + weakReference.get());
        System.out.println("Soft Reference (after second GC): " + softReference.get());
        System.out.println("Phantom Reference (after second GC): " + phantomReference.get());
    }
}

