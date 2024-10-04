package JavaMemoryManagement;

import java.io.*;
import java.lang.reflect.Method;

public class CustomClassLoaderDemo {

    // Custom ClassLoader implementation
    static class MyClassLoader extends ClassLoader {
        private String directory;

        public MyClassLoader(String directory) {
            this.directory = directory;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            String filePath = directory + File.separator + name.replace('.', File.separatorChar) + ".class";
            try {
                byte[] classData = loadClassData(filePath);
                return defineClass(name, classData, 0, classData.length);
            } catch (IOException e) {
                throw new ClassNotFoundException("Class not found: " + name, e);
            }
        }

        private byte[] loadClassData(String filePath) throws IOException {
            try (InputStream input = new FileInputStream(filePath);
                 ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                return output.toByteArray();
            }
        }
    }

    public static void main(String[] args) {
        // Define the directory where the compiled classes are located
        String classDirectory = "path/to/classes"; // Update this path as necessary
        String className = "JavaMemoryManagement.DynamicClass"; // Full name with package

        try {
            MyClassLoader classLoader = new MyClassLoader(classDirectory);

            // Load a class dynamically
            Class<?> dynamicClass = classLoader.loadClass(className);

            // Create an instance and invoke a method using reflection
            Object instance = dynamicClass.getDeclaredConstructor().newInstance();
            Method method = dynamicClass.getMethod("sayHello");
            method.invoke(instance);

            // Clear the reference to the classLoader to make it eligible for garbage collection
            classLoader = null;

            // Suggest garbage collection
            System.gc();

            // Pause to allow GC to complete
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
