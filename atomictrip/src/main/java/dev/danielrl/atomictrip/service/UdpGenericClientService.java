package dev.danielrl.atomictrip.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import dev.danielrl.atomictrip.dto.ServiceResponse;

public class UdpGenericClientService implements GenericClientService {

    private static final String SERVER_ADDRESS = "localhost";


    public ServiceResponse sendMessage(String message, int port) {
        System.out.println("UDP Client Started");
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            DatagramPacket sendPacket;
            InetAddress inetAddress = InetAddress.getByName(SERVER_ADDRESS);

            byte[] sendMessage;
            byte[] receivemessage = new byte[1024];

            sendMessage = message.getBytes();

            System.out.println("Sending message: " + message + " to " + inetAddress + port);
            sendPacket = new DatagramPacket(
                    sendMessage, sendMessage.length,
                    inetAddress, port);
            // informativo
            System.out.println("pacotes: " + (int) Math.ceil((double) sendMessage.length / 1024));

            clientSocket.send(sendPacket);

            // Receber resposta do Servidor - Conta Aberta
            DatagramPacket receivepacket = new DatagramPacket(receivemessage, receivemessage.length);
            clientSocket.receive(receivepacket);
            message = new String(receivepacket.getData());
            System.out.println("Received message: " + message);

            clientSocket.close();
            return ServiceResponse.valueOf(message.trim());
        } catch (Exception ex) {
            return ServiceResponse.NO_RESULTS;
        }
    }

}
