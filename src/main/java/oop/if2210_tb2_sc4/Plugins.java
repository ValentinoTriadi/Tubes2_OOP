package oop.if2210_tb2_sc4;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Plugins {
    private List<Class<?>> listOfClass;

    public Plugins() {
        listOfClass = new ArrayList<>();
    }

    private void addClass(Class<?> clazz) {
        listOfClass.add(clazz);
    }

    public List<Class<?>> getListOfClass() {
        return listOfClass;
    }

    private  Class<?> getClassByName(String name) {
        for (Class<?> clazz : listOfClass) {
            if (clazz.getName().equals(name)) {
                return clazz;
            }
        }
        return null;
    }

    private void removeClass(Class<?> clazz) {
        listOfClass.remove(clazz);
    }

    private void removeAllClass() {
        listOfClass.clear();
    }

    public void loadJar(String path, PluginsType type) {
        try {
            Class<?> clazz;
            if (type == PluginsType.JSON) {
                clazz = JarLoader.getInstance().loadJSONJar(path);
            } else {
                assert type == PluginsType.XML;
                clazz = JarLoader.getInstance().loadXMLJar(path);
            }
            addClass(clazz);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void runLoad(PluginsType type){
        try {
            // Get the class
            Class<?> clazz;
            if (type == PluginsType.JSON) {
                clazz = getClassByName("SaveLoadJSON");
            } else {
                assert type == PluginsType.XML;
                clazz = getClassByName("SaveLoadXML");
            }
            assert clazz != null;

            // Get the method
            Method method = clazz.getDeclaredMethod("Load");

            // Set access
            method.setAccessible(true);

            // Run the method
            method.invoke(null);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
