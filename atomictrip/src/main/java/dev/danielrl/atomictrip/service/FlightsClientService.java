package dev.danielrl.atomictrip.service;

import dev.danielrl.atomictrip.dto.TripDetailsDTO;

public class FlightsClientService {

    private static final String SERVER_ADDRESS = "localhost";
    private int SERVER_PORT = 12345;

    public FlightsClientService(int port) {
        this.SERVER_PORT = port;
    }

    public String bookFlight(TripDetailsDTO tripDetails) {
        // Implementar a lógica para enviar a solicitação de reserva de voo para o servidor UDP
        return null;
    }

    private String sendUdpMessage(String message) {
        // Implementar a lógica para enviar e receber mensagens via UDP
        return null;
    }
}
