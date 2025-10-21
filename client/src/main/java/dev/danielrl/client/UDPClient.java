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
			DatagramPacket sendPacket;
			InetAddress inetAddress = InetAddress.getByName("localhost");

			byte[] sendMessage;
			byte[] receivemessage = new byte[1024];


			while (true) {
				System.out.print("Enter a message: ");
				String message = scanner.nextLine();
				if ("quit".equalsIgnoreCase(message)) {
					break;
				}
				if ("test".equalsIgnoreCase(message)){
					message = "book;NYC;LA;2024-12-20;2025-01-05";
				}
				if ("bank".equalsIgnoreCase(message)){
					message = "criar;1234;0";
				}
				if ("reserve".equalsIgnoreCase(message)){
					message = "reserveflight;2025-10-12;NATAL;RECIFE";
				}
				if("confirm".equalsIgnoreCase(message)){
					message = "confirmflight;2025-10-12;NATAL;RECIFE";
				}
				if("cancel".equalsIgnoreCase(message)){
					message = "cancelflight;2025-10-12;NATAL;RECIFE";
				}
				if("bookflight".equalsIgnoreCase(message)){
					message = "bookflight;2025-12-20T00:00:00Z;2025-12-27T00:00:00Z;NATAL;RECIFE;";
				}
				sendMessage = message.getBytes();

				System.out.println("Sending message: " + message + " to " + inetAddress + ":8009");
				sendPacket = new DatagramPacket(
						sendMessage, sendMessage.length,
						inetAddress, 8009);
				// informativo		
				System.out.println("pacotes: " + (int) Math.ceil((double) sendMessage.length / 1024));

				clientSocket.send(sendPacket);

				//Receber resposta do Servidor - Conta Aberta
				DatagramPacket receivepacket = new DatagramPacket(receivemessage, receivemessage.length);
				clientSocket.receive(receivepacket);
				message = new String(receivepacket.getData());
				System.out.println(message);
			}
			clientSocket.close();
		} catch (IOException ex) {
		}
		System.out.println("UDP Client Terminating ");
	}
}
