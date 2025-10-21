package dev.danielrl.atomictrip.service;

import java.security.Provider.Service;

import dev.danielrl.atomictrip.dto.ServiceResponse;
import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Trip;

public class HotelsClientService {

    private static final String SERVER_ADDRESS = "localhost";
    private int SERVER_PORT = 12345;

    public HotelsClientService(int port) {
        this.SERVER_PORT = port;
    }

    public String bookHotelDays() {
        return "Hotels booked successfully.";
    }

    public ServiceResponse reserveHotelDays(TripDetailsDTO tripDetails) {
        System.out.println("Reserving hotel days...");
        return ServiceResponse.SUCCESS;
    }

    public ServiceResponse confirmHotelDays(TripDetailsDTO tripDetails) {
        System.out.println("Confirming hotel days...");
        return ServiceResponse.SUCCESS;
    }

    public void cancelHotelDays(TripDetailsDTO tripDetails) {
        System.out.println("Cancelling hotel days...");
    }
}
