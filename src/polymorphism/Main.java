package polymorphism;
import polymorphism.database.DatabaseClient;
import polymorphism.logging.FileLogger;
import polymorphism.udp.UdpClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws Throwable {
        DatabaseClient databaseClient = new DatabaseClient();
      //  HttpClient httpClient1  = new HttpClient("123.456.789.0");
       // HttpClient httpClient2 = new HttpClient("11.33.55.0");
        FileLogger fileLogger = new FileLogger();
        UdpClient udpClient = new UdpClient();

        String requestBody = "request data";

        List<Class<?>> methodParameterTypes = Arrays.asList(new Class<?>[]{String.class});

        Map<Object,Method> requestExecutor =
                groupExecutors(Arrays.asList(databaseClient,fileLogger,udpClient),methodParameterTypes);

        executeAll(requestExecutor,requestBody);

    }

    public static void executeAll(Map<Object,Method> requestExecutors,String requestBody) throws InvocationTargetException, IllegalAccessException {
        for (Map.Entry<Object,Method> requestExecutorEntry : requestExecutors.entrySet()){

            Object requestExecutor = requestExecutorEntry.getKey();
            Method method = requestExecutorEntry.getValue();

           Boolean result = (Boolean) method.invoke(requestExecutorEntry,requestBody);

           if (result != null && result.equals(Boolean.FALSE)){
               System.out.println("Received negative result. Aborting ... ");
               return;
           }
        }
    }

    public static Map<Object, Method> groupExecutors(List<Object> requestExecutors,List<Class<?>>methodParameterTypes){
        Map<Object,Method>instanceToMethod = new HashMap<>();

        for (Object requestExecutor : requestExecutors){
            Method[]allMethods = requestExecutor.getClass().getDeclaredMethods();

            for (Method method : allMethods){
                if (Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)){
                    instanceToMethod.put(requestExecutor,method);

                }
            }
        }
        return instanceToMethod;
    }
}
