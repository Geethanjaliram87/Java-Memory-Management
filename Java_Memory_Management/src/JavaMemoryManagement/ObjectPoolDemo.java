package JavaMemoryManagement;

import java.util.ArrayList;
import java.util.List;

// Class representing a simple object that will be pooled
class PooledObject {
    private String name;

    public PooledObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Object Pool class
class ObjectPool {
    private final List<PooledObject> availableObjects;
    private final List<PooledObject> usedObjects;

    public ObjectPool(int initialSize) {
        availableObjects = new ArrayList<>(initialSize);
        usedObjects = new ArrayList<>();

        // Initialize the pool with objects
        for (int i = 0; i < initialSize; i++) {
            availableObjects.add(new PooledObject("Object " + i));
        }
    }

    public PooledObject acquireObject() {
        if (availableObjects.isEmpty()) {
            System.out.println("No available objects in the pool. Creating a new one.");
            return new PooledObject("New Object");
        }

        // Get an object from the available pool
        PooledObject obj = availableObjects.remove(availableObjects.size() - 1);
        usedObjects.add(obj);
        return obj;
    }

    public void releaseObject(PooledObject obj) {
        usedObjects.remove(obj);
        availableObjects.add(obj);
    }

    public int getAvailableCount() {
        return availableObjects.size();
    }

    public int getUsedCount() {
        return usedObjects.size();
    }
}

public class ObjectPoolDemo {
    public static void main(String[] args) {
        ObjectPool objectPool = new ObjectPool(5); // Create an object pool with 5 initial objects

        // Acquire objects from the pool
        for (int i = 0; i < 10; i++) {
            PooledObject obj = objectPool.acquireObject();
            System.out.println("Acquired: " + obj.getName());
        }

        // Release some objects back to the pool
        for (int i = 0; i < 5; i++) {
            PooledObject obj = objectPool.acquireObject();
            System.out.println("Releasing: " + obj.getName());
            objectPool.releaseObject(obj);
        }

        // Final pool state
        System.out.println("Available objects in the pool: " + objectPool.getAvailableCount());
        System.out.println("Used objects in the pool: " + objectPool.getUsedCount());
    }
}
