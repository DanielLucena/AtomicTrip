package dev.danielrl.atomictrip.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.ZonedDateTime;
import java.util.StringTokenizer;

import dev.danielrl.atomictrip.dto.Message;
import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Location;
import dev.danielrl.atomictrip.service.TripService;

public class UdpServer implements Server {

	private TripService tripService;
	//ExecutorService poolvthreads = Executors.newVirtualThreadPerTaskExecutor();

	@Override
	public void start(String port) {
		tripService = new TripService();
		System.out.println("UDP Server Bank started");
		try {
			DatagramSocket serversocket = new DatagramSocket(Integer.parseInt(port));
			while (true) {
				byte[] receivemessage = new byte[1024];
				DatagramPacket receivepacket = new DatagramPacket(receivemessage, receivemessage.length);
				serversocket.receive(receivepacket);
				System.out.println("PACOTE RECEBIDO");
				String message = new String(receivepacket.getData(), 0, receivepacket.getLength());
				//poolvthreads.submit(() -> {
					processarMensagem(message, receivepacket, serversocket);
				//});

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException nfe) {
			System.out.println("Erro ao converter numero: " + nfe.getMessage());

		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
		System.out.println("UDP Bank server terminating");
	}


	private void processarMensagem(String message, DatagramPacket receivepacket, DatagramSocket serversocket) {
		String operacao = null;
		String dataIda = null;
		String dataVolta = null;
		String origem = null;
		String destino = null;
		String resultadoOp = message;
		try {
			StringTokenizer tokenizer = new StringTokenizer(message, ";");
			while (tokenizer.hasMoreElements()) {
				operacao = tokenizer.nextToken();
				dataIda = tokenizer.nextToken();
				dataVolta = tokenizer.nextToken();
				origem = tokenizer.nextToken();
				destino = tokenizer.nextToken().trim();
			}
			switch (operacao) {
				case "bookflight":
					resultadoOp = new TripDetailsDTO( Location.valueOf(origem), Location.valueOf(destino), ZonedDateTime.parse(dataIda), ZonedDateTime.parse(dataVolta)).toString();
					break;
				case "bookhotel":
					resultadoOp = new TripDetailsDTO( Location.valueOf(origem), Location.valueOf(destino), ZonedDateTime.parse(dataIda), ZonedDateTime.parse(dataVolta)).toString();
					break;
				case "booktrip":
					resultadoOp = new TripDetailsDTO( Location.valueOf(origem), Location.valueOf(destino), ZonedDateTime.parse(dataIda), ZonedDateTime.parse(dataVolta)).toString();
					break;
				default:
					resultadoOp = "Operacao Invalida";
			}
			System.out.println(
					"Operacao realizada:" + operacao + " - dataIda: " + dataIda + " - dataVolta: " + dataVolta + " - origem: " + origem + " - destino: " + " - " + receivepacket.getAddress());
			String reply = "Confirmo Recebimento de:" + resultadoOp;
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
