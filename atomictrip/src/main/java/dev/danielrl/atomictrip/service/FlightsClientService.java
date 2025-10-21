package dev.danielrl.atomictrip.service;

import java.security.Provider.Service;
import java.time.ZonedDateTime;

import dev.danielrl.atomictrip.dto.ServiceResponse;
import dev.danielrl.atomictrip.dto.TrechoDTO;
import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Location;

public class FlightsClientService {

    private static final String SERVER_ADDRESS = "localhost";
    private int SERVER_PORT = 12345;

    public FlightsClientService(int port) {
        this.SERVER_PORT = port;
    }

    public String bookFlights(TripDetailsDTO tripDetails) {
        ServiceResponse reserva = reserveFlights(tripDetails);
        if (reserva == ServiceResponse.SUCCESS) {
            ServiceResponse confirma = confirmFlights(tripDetails);
            if(confirma == ServiceResponse.SUCCESS) {
                return "Flights booked successfully.";
            }
        }
        return "Failed to book flights.";

    }

    public ServiceResponse reserveFlights(TripDetailsDTO tripDetails) {
        TrechoDTO trechoIda = new TrechoDTO(tripDetails.getDataIda(), tripDetails.getOrigem(),
                tripDetails.getDestino());
        TrechoDTO trechoVolta = new TrechoDTO(tripDetails.getDataVolta(), tripDetails.getDestino(),
                tripDetails.getOrigem());
        System.out.println("Reserving trip with details: " + tripDetails);

        ServiceResponse reservaIda = reserveOneWayFlight(trechoIda);
        if (reservaIda != ServiceResponse.SUCCESS) {
            cancelOneWayFlight(trechoIda);
            return ServiceResponse.FAILURE;
        } else {
            ServiceResponse reservaVolta = reserveOneWayFlight(trechoVolta);
            if (reservaVolta != ServiceResponse.SUCCESS) {
                cancelOneWayFlight(trechoVolta);
                return ServiceResponse.FAILURE;
            }
            return ServiceResponse.SUCCESS;
        }
            
    }

    public ServiceResponse confirmFlights(TripDetailsDTO tripDetails) {
        TrechoDTO trechoIda = new TrechoDTO(tripDetails.getDataIda(), tripDetails.getOrigem(),
                tripDetails.getDestino());
        TrechoDTO trechoVolta = new TrechoDTO(tripDetails.getDataVolta(), tripDetails.getDestino(),
                tripDetails.getOrigem());

        ServiceResponse confirmaIda = confirmOneWayFlight(trechoIda);
        ServiceResponse confirmaVolta = confirmOneWayFlight(trechoVolta);

        if (confirmaIda == ServiceResponse.SUCCESS && confirmaVolta == ServiceResponse.SUCCESS) {
            return ServiceResponse.SUCCESS;
        } else {
            return ServiceResponse.FAILURE;
        }
    }

    public ServiceResponse cancelFlights(TripDetailsDTO tripDetails) {
        TrechoDTO trechoIda = new TrechoDTO(tripDetails.getDataIda(), tripDetails.getOrigem(),
                tripDetails.getDestino());
        TrechoDTO trechoVolta = new TrechoDTO(tripDetails.getDataVolta(), tripDetails.getDestino(),
                tripDetails.getOrigem());

        ServiceResponse cancelIda = cancelOneWayFlight(trechoIda);
        ServiceResponse cancelVolta = cancelOneWayFlight(trechoVolta);

        if (cancelIda == ServiceResponse.SUCCESS && cancelVolta == ServiceResponse.SUCCESS) {
            return ServiceResponse.SUCCESS;
        } else {
            return ServiceResponse.FAILURE;
        }
    }

    public ServiceResponse reserveOneWayFlight(TrechoDTO trecho) {
        String message = "reserveflight;" + trecho.data() + ";" + trecho.origem() + ";" + trecho.destino();
        GenericClientService udpClient = new UdpGenericClientService();
        return udpClient.sendMessage(message, SERVER_PORT);
    }

    public ServiceResponse confirmOneWayFlight(TrechoDTO trecho) {
        String message = "confirmflight;" + trecho.data() + ";" + trecho.origem() + ";" + trecho.destino();
        GenericClientService udpClient = new UdpGenericClientService();
        return udpClient.sendMessage(message, SERVER_PORT);
    }

    public ServiceResponse cancelOneWayFlight(TrechoDTO trecho) {
        String message = "cancelflight;" + trecho.data() + ";" + trecho.origem() + ";" + trecho.destino();
        GenericClientService udpClient = new UdpGenericClientService();
        return udpClient.sendMessage(message, SERVER_PORT);
    }

    
}
