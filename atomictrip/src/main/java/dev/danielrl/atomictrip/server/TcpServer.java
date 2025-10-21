package dev.danielrl.atomictrip.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.time.ZonedDateTime;



import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Location;
import dev.danielrl.atomictrip.service.FlightsClientService;
import dev.danielrl.atomictrip.service.TripService;

public class TcpServer implements Server {

    private TripService tripService;
    private FlightsClientService flightsClientService;

    @Override
    public void start(String port) {
        tripService = new TripService();
        flightsClientService = new FlightsClientService(8010);
        System.out.println("TCP Server Trip started");
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Integer.parseInt(port), 50);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar o servidor TCP: " + e.getMessage());
        }

        while (true) {
            try {
                Socket conexao = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
                // System.out.println("Cliente conectado: " + conexao.getInetAddress());
                String message = input.readLine();
                System.out.println("Mensagem recebida: " + message);
                
                String reply = processarMensagem(message, conexao.getInetAddress().toString());
                PrintWriter output = new PrintWriter(conexao.getOutputStream(), true);
                output.println("Server response: " + reply);
                output.flush();
                conexao.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String processarMensagem(String message, String cliente) {
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

            TripDetailsDTO tripDetails = new TripDetailsDTO(
                    Location.valueOf(origem),
                    Location.valueOf(destino),
                    ZonedDateTime.parse(dataIda),
                    ZonedDateTime.parse(dataVolta));

            switch (operacao) {
                case "bookflight":
                    resultadoOp = tripDetails.toString();
                    break;
                case "bookhotel":
                    resultadoOp = tripDetails.toString();
                    break;
                case "booktrip":
                    resultadoOp = tripDetails.toString();
                    break;
                default:
                    resultadoOp = "Operacao Invalida";
            }

            System.out.println("Operacao realizada: " + operacao +
                    " - dataIda: " + dataIda +
                    " - dataVolta: " + dataVolta +
                    " - origem: " + origem +
                    " - destino: " + destino +
                    " - cliente: " + cliente);

            return "Confirmo Recebimento de: " + resultadoOp;

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao processar mensagem: " + e.getMessage();
        }
    }
}
