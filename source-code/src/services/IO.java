package services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class IO {
    private static final String FILE_CONFIG = "/config.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            String currentDir = System.getProperty("user.dir");
            inputStream = new FileInputStream(currentDir + FILE_CONFIG);

            // load properties from file
            properties.load(inputStream);

            return properties;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // close objects
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void print(Object x) {
        System.out.print(x);
    }

    public static void println(Object x) {
        System.out.println(x);
    }

    public static List<String> getAllFiles(String filePath) {
        List<String> results = new ArrayList<String>();


        File[] files = new File(filePath).listFiles();

        for (File file : files) {
            results.add(file.getAbsolutePath());
        }
        return results;
    }

    public static List<String> getAllJarFiles(String filePath) {
        List<String> results = new ArrayList<String>();


        File[] files = new File(filePath).listFiles();

        for (File file : files) {
            if (file.isFile() && file.getAbsolutePath().endsWith(".jar")) {
                results.add(file.getAbsolutePath());
            }
        }
        return results;
    }

    public static boolean stringEqual(String x, String y) {
        return x != null && x.equals(y);
    }

    public static String getFileName(String filePath) {
        File f = new File(filePath);
        return f.getName();
    }

    public static String getOutputPath() {
        Properties properties = IO.getProperties();
        String dotOutput = properties.getProperty("dot_output");
        return dotOutput;
    }

    public static String getProcessedPath() {
        Properties properties = IO.getProperties();
        String dotOutput = properties.getProperty("processed_files");
        return dotOutput;
    }

    public static String getCannotProcessPath() {
        Properties properties = IO.getProperties();
        String dotOutput = properties.getProperty("cannot_process_files");
        return dotOutput;
    }
}
