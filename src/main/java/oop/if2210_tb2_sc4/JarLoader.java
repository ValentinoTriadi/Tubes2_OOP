package oop.if2210_tb2_sc4;

import oop.if2210_tb2_sc4.save_load.SaveLoadAnnotation;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.Map;
import oop.if2210_tb2_sc4.save_load.SaveLoad;

public class JarLoader {
    private static final JarLoader instance = new JarLoader();

    private JarLoader() {
    }

    public static JarLoader getInstance(){
        return instance;
    }

    @Nullable
    Map.Entry<String, SaveLoad> loadJar(String path){
        try {
            System.out.println("Loading JAR from path: " + path);

            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                path = "/" + path;
            }

            // Create a new URLClassLoader
            URL[] urls = {new URL("file://" + path)};
            URLClassLoader loader = new URLClassLoader(urls, this.getClass().getClassLoader());

            // Load class that implements SaveLoad in the JAR file
            ServiceLoader<SaveLoad> serviceLoader = ServiceLoader.load(SaveLoad.class, loader);

            // Check if any implementation is found
            if (!serviceLoader.iterator().hasNext()) {
                System.out.println("No implementations of SaveLoad found in the JAR.");
                return null;
            }

            // Get the first implementation found
            SaveLoad saveLoad = serviceLoader.iterator().next();
            Class<?> saveLoadClass = saveLoad.getClass();
            System.out.println("Found implementation class: " + saveLoadClass.getName());

            SaveLoadAnnotation annotation = saveLoadClass.getAnnotation(SaveLoadAnnotation.class);

            if (annotation == null) {
                System.out.println("No SaveLoadAnnotation found on the implementation class.");
                return null;
            }

            String type = annotation.type().toLowerCase();
            // Return the class
            return Map.entry(type, saveLoad);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
