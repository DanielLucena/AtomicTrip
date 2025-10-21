package dev.danielrl.hotels;

import dev.danielrl.hotels.server.Server;
import dev.danielrl.hotels.server.ServerFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Server server = ServerFactory.ServerFactory("udp");

        server.start("8020");
    }
}