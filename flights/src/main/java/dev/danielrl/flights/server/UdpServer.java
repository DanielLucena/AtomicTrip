package dev.danielrl.flights.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import dev.danielrl.flights.dto.Message;
import dev.danielrl.flights.model.Flight;
import dev.danielrl.flights.service.FlightService;

public class UdpServer implements Server {

    private FlightService flightService;
	//ExecutorService poolvthreads = Executors.newVirtualThreadPerTaskExecutor();
	public void start(int port) {
		this.flightService = new FlightService();
		System.out.println("Starting UDP server...");
		try {
			DatagramSocket serverSocket = new DatagramSocket(port);
			while (true) {
				byte[] receiveMessage = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
				serverSocket.receive(receivePacket);
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
				//poolvthreads.submit(() -> {
					processarMensagem(message, receivePacket, serverSocket);
				//});
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Erro ao converter numero: " + nfe.getMessage());

		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
		System.out.println("UDP Flights server terminating");
	}


	private void processarMensagem(String message, DatagramPacket receivePacket, DatagramSocket serverSocket) {
		System.out.println("Received message: " + message);
		String verb = null;
		String path = null;
		String body = null;
		String resultadoOp = message;
		try {
			StringTokenizer tokenizer = new StringTokenizer(message, ";");
			while (tokenizer.hasMoreElements()) {
				verb = tokenizer.nextToken();
				path = tokenizer.nextToken();
				body = tokenizer.nextToken().trim();
			}
			Message msgObj = new Message(verb, path, body);

			switch (path) {
				case "bookFlight":
					flightService.bookFlight(body);
					break;
			}
			System.out.println(
					"Operacao realizada:" + path + "-" + msgObj.getBody() + "-" + receivePacket.getAddress());
			String reply = "Confirmo Recebimento de:" + resultadoOp;
			byte[] replymsg = reply.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(replymsg, replymsg.length,
					receivePacket.getAddress(), receivePacket.getPort());
			serverSocket.send(sendPacket);
		} catch (IOException e) {
				e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Erro ao converter numero: " + nfe.getMessage());
		}
	}
}