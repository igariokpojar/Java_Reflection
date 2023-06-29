package polymorphism.logging;

public class FileLogger {
    public void sendRequest(String data){
        System.out.println(String.format("Data $s was logged to the file system",data));
    }
}
