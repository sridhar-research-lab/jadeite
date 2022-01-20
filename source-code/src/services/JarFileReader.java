package services;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;

public class JarFileReader {

    public static Set<String> getClassNamesFromJarFile(File givenFile) throws IOException {
        Set<String> classNames = new HashSet<>();
        try (java.util.jar.JarFile jarFile = new java.util.jar.JarFile(givenFile)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry jarEntry = e.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    classNames.add(className);
                }
            }
            return classNames;
        }catch (Exception e){
            return null;
        }
    }

    public static Set<Class> getClassesFromJarFile(File jarFile) throws IOException, ClassNotFoundException {
        Set<String> classNames = getClassNamesFromJarFile(jarFile);
        Set<Class> classes = new HashSet<>(classNames.size());
        try (URLClassLoader cl = URLClassLoader.newInstance(new URL[] { new URL("jar:file:" + jarFile + "!/") })) {
            for (String name : classNames) {
                Class clazz = cl.loadClass(name); // Loading the class by its name
                classes.add(clazz);
            }
        }
        return classes;
    }
}
