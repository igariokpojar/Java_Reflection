package app;

import annotations.InitializerClass;
import annotations.InitializerMethod;
import annotations.RetryOperation;
import annotations.ScanPackages;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


@ScanPackages({"app","app.configs","app.databases","app.http"})
    public class Main {
    public static void main(String[] args) throws Throwable{

        initialize();

    }

    public static void initialize() throws Throwable {
        ScanPackages scanPackage = Main.class.getAnnotation(ScanPackages.class);

        if (scanPackage == null || scanPackage.value().length == 0){
            return;
        }
        List<Class<?>> classes = getAllClasses(scanPackage.value());

        for (Class<?>clazz:classes){
            if (!clazz.isAnnotationPresent(InitializerClass.class)){
                continue;
            }
            List<Method> methods = getAllInitializingMethods(clazz);

            Object instance= clazz.getDeclaredConstructor().newInstance();

            for (Method method : methods){
                method.invoke(instance,methods);
            }
        }
    }
    private static void callInitializingMethod(Object instance,Method method) throws Throwable {
        RetryOperation retryOperation = method.getAnnotation(RetryOperation.class);

        int numberOfRetries = retryOperation == null ? 0 : retryOperation.numberOfRetries();
        while (true){
            try {
                method.invoke(instance);
                break;
            }catch (InvocationTargetException e){
                Throwable targetException = e.getTargetException();
                if (numberOfRetries> 0 && Set.of(retryOperation.retryException()).contains(targetException.getClass())){
                    numberOfRetries--;

                System.out.println("Retrying ...");
                Thread.sleep(retryOperation.durationBetweenRetriesMs());
            } else if (retryOperation !=null) {
                    throw new Exception(retryOperation.failureMessage(),targetException);

                }else {
                    throw targetException;
                }
            }

        }

    }


    private static List<Method> getAllInitializingMethods(Class<?> clazz){

        List<Method> initializingMethods = new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()){
            if (method.isAnnotationPresent(InitializerMethod.class)){
                initializingMethods.add(method);


            }
        }
        return initializingMethods;
    }

    public static  List<Class<?>> getAllClasses(String ... packageNames) throws URISyntaxException, IOException, ClassNotFoundException {
        List<Class<?>> allClasses = new ArrayList<>();

        for (String packageName : packageNames){
            String packageRelativePath = packageName.replace('.','/');

            URI packageUri = Main.class.getResource(packageRelativePath).toURI();

            if (packageUri.getScheme().equals("file")){
                Path packageFullPath = Paths.get(packageUri);
                allClasses.addAll(getAllPackageClasses(packageFullPath,packageName));
            } else if (packageUri.getScheme().equals("jar")){
                FileSystem fileSystem = FileSystems.newFileSystem(packageUri,Collections.emptyMap());

                  Path packageFullPathInJar = fileSystem.getPath(packageRelativePath);
                  allClasses.addAll(getAllPackageClasses(packageFullPathInJar,packageName));

                  fileSystem.close();
            }
        }
               return allClasses;
    }

    private static  List<Class<?>> getAllPackageClasses(Path packagePath, String packageName)throws IOException,ClassNotFoundException{
        if (!Files.exists(packagePath)){
            return Collections.emptyList();
        }
        List<Path> files = Files.list(packagePath)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());

        List<Class<?>> classes = new ArrayList<>();

        for (Path filePath : files){
            String fileName = filePath.getFileName().toString();

            if (fileName.endsWith("class")){
                String classFullName = packageName + "." + fileName.replaceFirst("\\.class$","");
                Class<?> clazz = Class.forName(classFullName);
                classes.add(clazz);
            }
        }
        return classes;
    }

}
