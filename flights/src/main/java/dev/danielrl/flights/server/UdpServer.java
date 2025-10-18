package dev.danielrl.flights.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZonedDateTime;
import java.util.StringTokenizer;

import dev.danielrl.flights.dto.FlightResponse;
import dev.danielrl.flights.model.Flight;
import dev.danielrl.flights.model.Location;
import dev.danielrl.flights.service.FlightService;

public class UdpServer implements Server {

	private FlightService flightService;
	// ExecutorService poolvthreads = Executors.newVirtualThreadPerTaskExecutor();

	@Override
	public void start(String port) {
		flightService = new FlightService();
		System.out.println("UDP Server Flight started");
		try {
			DatagramSocket serversocket = new DatagramSocket(Integer.parseInt(port));
			while (true) {
				byte[] receivemessage = new byte[1024];
				DatagramPacket receivepacket = new DatagramPacket(receivemessage, receivemessage.length);
				serversocket.receive(receivepacket);
				System.out.println("PACOTE RECEBIDO");
				String message = new String(receivepacket.getData(), 0, receivepacket.getLength());
				// poolvthreads.submit(() -> {
				processarMensagem(message, receivepacket, serversocket);
				// });

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Erro ao converter numero: " + nfe.getMessage());

		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
		System.out.println("UDP Flight server terminating");
	}

	private void processarMensagem(String message, DatagramPacket receivepacket, DatagramSocket serversocket) {
		String operacao = null;
		String data = null;
		String origem = null;
		String destino = null;
		FlightResponse flightResponse = FlightResponse.NO_RESULTS;
		try {
			StringTokenizer tokenizer = new StringTokenizer(message, ";");
			while (tokenizer.hasMoreElements()) {
				operacao = tokenizer.nextToken();
				data = tokenizer.nextToken();
				origem = tokenizer.nextToken();
				destino = tokenizer.nextToken().trim();

				Flight flight = new Flight(Location.valueOf(origem), Location.valueOf(destino), ZonedDateTime.parse(data+"T00:00:00Z"));

				switch (operacao) {
					case "reserveflight":
						flightResponse = flightService.reserveFlight(flight);
						break;
					case "confirmflight":
						flightResponse = flightService.confirmFlight(flight);
						break;
					case "cancelflight":
						flightResponse = flightService.cancelFlight(flight);
						break;
					default:
						flightResponse = FlightResponse.FAILURE;
				}
			}

			System.out.println(
					"Operacao realizada:" + operacao + " - Data: " + data + " - Origem: " + origem + " - Destino: " + destino + " - " + receivepacket.getAddress());
			String reply = flightResponse.name();
			byte[] replymsg = reply.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(replymsg, replymsg.length,
					receivepacket.getAddress(), receivepacket.getPort());
			serversocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Erro ao converter numero: " + nfe.getMessage());
		}
	}
}
