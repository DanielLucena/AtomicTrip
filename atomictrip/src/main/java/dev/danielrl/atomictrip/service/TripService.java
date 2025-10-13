package dev.danielrl.atomictrip.service;

import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Trip;

public class TripService {


    public void bookTrip(TripDetailsDTO tripDetails) {
        // Lógica para reservar uma viagem
        System.out.println("Booking trip with details: " + tripDetails);
    }
}
