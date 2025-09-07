package dev.danielrl.atomictrip;

import dev.danielrl.atomictrip.server.ServerFactory;
import dev.danielrl.atomictrip.server.Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // String protocol = System.getenv("PROTOCOL");
        // int port = Integer.parseInt(System.getenv("PORT"));
        Server server = ServerFactory.ServerFactory("udp");
        server.start(8009);

    }
}