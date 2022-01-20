package services;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class SearchMethodInJarFile {

    public static Map<String, List<Method>> getMethodListOfJarFile(String jarFile) {
        try {
            HashMap<String, String> methods = new HashMap<>();
            Set<Class<?>> classes = new HashSet<>();

            URL jarUrl = new File(jarFile).toURI().toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl});
            JarFile jar = new JarFile(jarFile);

//            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ) {
//                JarEntry entry = entries.nextElement();
//                String file = entry.getName();
//
//                if (file.endsWith(".class")) {
//
//                    String classname =
//                            file.replace('/', '.').substring(0, file.length() - 6).split("\\$")[0];
//
//                    try {
//                        if(classname.equals("Main.class")){
//                            String a = "";
//                        }
//                        Class<?> c = loader.loadClass(classname);
//                        classes.add(c);
//                    } catch (Throwable e) {
//                        System.out.println("WARNING: failed to instantiate " + classname + " from " + file);
//                    }
//                }
//            }
            Set<Class> jarClasses = JarFileReader.getClassesFromJarFile(new File(jarFile));
            Map<String, List<Method>> collected =
                    jarClasses.stream().collect(Collectors.toMap(Class::getName, clz -> {
                        try {
                            List<Method> mtds = new ArrayList<>(Arrays.asList(clz.getDeclaredMethods()));
                            mtds.addAll(Arrays.asList(clz.getMethods()));
                            for (Method mtd : mtds) {
                                String[] mtd_list = mtd.toString().split("\\(");
                                if (mtd_list.length > 0) {
                                    String[] mtd_array = mtd_list[0].split("\\.");
                                    methods.put(clz.getName(), mtd_array[mtd_array.length - 1]);
                                }
                            }
                            return mtds;
                        } catch (NoClassDefFoundError e) {
                            return null;
                        }
                    }));
            return collected;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMethodClass(String jarFile) {
        String methodName = null;
        try {
            Map<String, List<Method>> methodClasses = SearchMethodInJarFile.getMethodListOfJarFile(jarFile);
            if (methodClasses == null) {
                return null;
            }
            for (String methodClass : methodClasses.keySet()) {
                List<Method> methods = methodClasses.get(methodClass);
                for (Method method : methods) {
                    String[] mtd_list = method.toString().split("\\(");
                    if (mtd_list.length > 0) {
                        String[] mtd_array = mtd_list[0].split("\\.");
                        String meth = mtd_array[mtd_array.length - 1];
                        if (meth.equals("init") || meth.equals("main") || meth.equals("startApp") || meth.equals("run")) {
                            methodName = methodClass;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return methodName;
    }
}