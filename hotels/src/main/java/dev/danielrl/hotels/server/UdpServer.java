package dev.danielrl.hotels.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZonedDateTime;
import java.util.StringTokenizer;

import dev.danielrl.hotels.dto.ServiceResponse;
import dev.danielrl.hotels.model.HotelFullReservation;
import dev.danielrl.hotels.model.Location;
import dev.danielrl.hotels.service.HotelService;

public class UdpServer implements Server {

	private HotelService hotelService;
	// ExecutorService poolvthreads = Executors.newVirtualThreadPerTaskExecutor();

	@Override
	public void start(String port) {
		hotelService = new HotelService();
		System.out.println("UDP Server Hotel started");
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
		System.out.println("UDP Hotel server terminating");
	}

	private void processarMensagem(String message, DatagramPacket receivepacket, DatagramSocket serversocket) {
		String operacao = null;
		String dataIda = null;
        String dataVolta = null;
		String destino = null;
		ServiceResponse hotelResponse = ServiceResponse.NO_RESULTS;
		try {
			StringTokenizer tokenizer = new StringTokenizer(message, ";");
			while (tokenizer.hasMoreElements()) {
				operacao = tokenizer.nextToken();
				dataIda = tokenizer.nextToken();
				dataVolta = tokenizer.nextToken();
				destino = tokenizer.nextToken().trim();

				HotelFullReservation hotel = new HotelFullReservation(Location.valueOf(destino), ZonedDateTime.parse(dataIda+"T00:00:00Z"), ZonedDateTime.parse(dataVolta+"T00:00:00Z"));

				switch (operacao) {
					case "reservehotel":
						hotelResponse = hotelService.reserveHotel(hotel);
						break;
					case "confirmhotel":
						hotelResponse = hotelService.confirmHotel(hotel);
						break;
					case "cancelhotel":
						hotelResponse = hotelService.cancelHotel(hotel);
						break;
					default:
						hotelResponse = ServiceResponse.FAILURE;
				}
			}

			System.out.println(
					"Operacao realizada:" + operacao + " - Data: " + dataIda + " - Data Volta: " + dataVolta + " - Destino: " + destino + " - " + receivepacket.getAddress());
			String reply = hotelResponse.name();
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
