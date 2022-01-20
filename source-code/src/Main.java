import java.io.IOException;
import java.util.List;
import java.util.Properties;

import services.IO;
import services.Processor;
import services.SearchMethodInJarFile;

public class Main {

    public static void main(String[] args) {
        try {
            Properties properties = IO.getProperties();
            String jarPath = properties.getProperty("jar_path");
            List<String> jarFiles = IO.getAllJarFiles(jarPath);
            int jarFileSize = jarFiles.size();
            int i = 0;
            for (String jarFilePath : jarFiles) {
                i++;
                IO.println("Processing file: " + i + "/" + jarFileSize);
                IO.println(jarFilePath);
                String methodClass;
                try {
                    methodClass = SearchMethodInJarFile.getMethodClass(jarFilePath);
                } catch (Error e) {
                    continue;
                } catch (Exception ex) {
                    continue;
                }
                if (methodClass != null) {
                    Processor.process(methodClass, jarFilePath);
                }
//                else{
//                    try{
//                        Processor.process("Main.class", jarFilePath);
//                    }catch(Error er){
//                        continue;
//                    }catch (IOException ex) {
//                        continue;
//                    }
//                }
            }
            IO.println("----------------------Complete---------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
