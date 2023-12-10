package app.http;

import annotations.InitializerClass;
import annotations.InitializerMethod;

@InitializerClass
public class ServiceRegistry {

    @InitializerMethod
    public void registryService(){
        System.out.println("service successfully registered");
    }
}
