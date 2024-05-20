package oop.if2210_tb2_sc4;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.ArrayList;

public class JarLoader {
    private static final JarLoader instance = new JarLoader();

    private JarLoader() {
    }

    public static JarLoader getInstance(){
        return instance;
    }

    @Nullable
    Class<?> loadJSONJar(String path){
        try {
            // Create a new classloader
            ClassLoader loader = new URLClassLoader(new URL[]{new URL("file://" + path)}, this.getClass().getClassLoader());

            // Return the class
            return loader.loadClass("SaveLoadJSON");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    Class<?> loadXMLJar(String path){
        try {
            // Create a new classloader
            ClassLoader loader = new URLClassLoader(new URL[]{new URL("file://" + path)}, this.getClass().getClassLoader());

            // Return the class
            return loader.loadClass("SaveLoadXML");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    private List<String> getMethodList(Class<?> clazz){
        // Get the method list
        try {
            Method[] methods = clazz.getDeclaredMethods();
            List<String> methodsName = new ArrayList<>();
            for (Method method : methods) {
                methodsName.add(method.getName());
            }
            return methodsName;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    private List<Class<?>[]> getConstructorParamList(Class<?> clazz){
        try {
            List<Class<?>[]> constructorParamList = new ArrayList<>();
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                constructorParamList.add(constructor.getParameterTypes());
            }
            return constructorParamList;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    private Object getInstanceFromClass(Class<?> clazz, Class<?>[] paramTypes, Object[] paramValues){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes);
            return constructor.newInstance(paramValues);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Nullable
    private Object callMethod(Class<?> clazz, String methodName, Class<?>[] paramTypes, Object[] paramValues){
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            return method.invoke(clazz, paramValues);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
