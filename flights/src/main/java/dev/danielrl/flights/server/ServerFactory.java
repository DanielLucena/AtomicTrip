package dev.danielrl.flights.server;

public class ServerFactory {
    public static Server ServerFactory(String protocol){
        switch (protocol) {
            case "udp":
                return new UdpServer();
            default:
                throw new IllegalArgumentException(" não é oferecido suporte ao protocolo: " + protocol  ); 
        }
    }

}
