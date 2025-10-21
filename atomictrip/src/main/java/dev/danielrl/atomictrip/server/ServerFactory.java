package dev.danielrl.atomictrip.server;

public class ServerFactory {
    public static Server ServerFactory(String protocol){
        switch (protocol) {
            case "udp":
                return new UdpServer();
            case "tcp":
                return new TcpServer();
            default:
                throw new IllegalArgumentException(" não é oferecido suporte ao protocolo: " + protocol  ); 
        }
    }

}
