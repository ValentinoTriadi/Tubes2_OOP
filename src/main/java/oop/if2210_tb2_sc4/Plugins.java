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

    public void run(PluginsType type){
        try {
            if (type == PluginsType.JSON) {
                Class<?> clazz = getClassByName("SaveLoadJSON");
                assert clazz != null;
                Method method = clazz.getDeclaredMethod("start");
                method.setAccessible(true);
                method.invoke(null);
            } else {
                assert type == PluginsType.XML;
                Class<?> clazz = getClassByName("SaveLoadXML");
                assert clazz != null;
                Method method = clazz.getDeclaredMethod("start");
                method.setAccessible(true);
                method.invoke(null);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
