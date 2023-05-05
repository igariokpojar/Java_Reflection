package init;

import java.net.InetSocketAddress;

public class ServerConfiguration {

    private static ServerConfiguration serverConfigurationInstance;
    private final InetSocketAddress serverAddress;
    private final String greetingMessage;


    public ServerConfiguration(int port, String greetingMessage) {
        this.serverAddress = new InetSocketAddress("localhost",port);
        this.greetingMessage = greetingMessage;

        if (serverConfigurationInstance == null){
            serverConfigurationInstance = this;
        }
    }

    public static ServerConfiguration getInstance(){ return serverConfigurationInstance; }

    public InetSocketAddress getServerAddress() {return  this.serverAddress; }

    public String getGreetingMessage() {return this.greetingMessage; }
}
