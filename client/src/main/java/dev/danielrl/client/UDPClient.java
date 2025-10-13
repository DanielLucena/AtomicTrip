package dev.danielrl.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class UDPClient {

	public UDPClient() {
		System.out.println("UDP Client Started");
		Scanner scanner = new Scanner(System.in);
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress inetAddress = InetAddress.getByName("localhost");
			byte[] sendMessage;
			while (true) {
				System.out.print("Enter a message: ");
				String message = scanner.nextLine();
				if ("quit".equalsIgnoreCase(message)) {
					break;
				}
				if ("test".equalsIgnoreCase(message)){
					message = "POST;/trips;{\"origem\":{\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"pais\":\"Brasil\"},\"destino\":{\"cidade\":\"Rio de Janeiro\",\"estado\":\"RJ\",\"pais\":\"Brasil\"},\"dataIda\":\"2023-12-20T10:00:00Z\",\"dataVolta\":\"2023-12-27T18:00:00Z\"}";
				}
				sendMessage = message.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(
						sendMessage, sendMessage.length,
						inetAddress, 8009);
				System.out.println("pacotes: " + (int) Math.ceil((double) sendMessage.length / 1024));
				clientSocket.send(sendPacket);
			}
			clientSocket.close();
		} catch (IOException ex) {
		}
		System.out.println("UDP Client Terminating ");
	}
}
