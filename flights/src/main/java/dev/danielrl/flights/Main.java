package dev.danielrl.flights;

import dev.danielrl.flights.server.Server;
import dev.danielrl.flights.server.ServerFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Server server = ServerFactory.ServerFactory("udp");

        server.start("8010");
    }
}