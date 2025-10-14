package dev.danielrl.atomictrip.service;

import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Trip;
import dev.danielrl.atomictrip.repository.TripRepository;

public class TripService {

    private TripRepository tripRepository;

    public TripService() {
        this.tripRepository = new TripRepository();
    }

    public void bookTrip(TripDetailsDTO tripDetails) {
        // Lógica para reservar uma viagem
        System.out.println("Booking trip with details: " + tripDetails);
        Trip trip = new Trip(tripDetails.getOrigem(), tripDetails.getDestino(), tripDetails.getDataVolta(), tripDetails.getDataIda());
        TripRepository tripRepository = new TripRepository();
        tripRepository.save(trip);
    }
}
