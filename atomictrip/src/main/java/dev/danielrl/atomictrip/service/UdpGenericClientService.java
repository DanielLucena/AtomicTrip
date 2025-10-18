package dev.danielrl.atomictrip.service;

public class UdpGenericClientService implements GenericClientService {

    private static final String SERVER_ADDRESS = "localhost";
    private int SERVER_PORT = 12345;

    public UdpGenericClientService(int port) {
        this.SERVER_PORT = port;
    }

    @Override
    public String sendMessage(String message) {
        // Implementar a lógica para enviar e receber mensagens via UDP
        return null;
    }

}
