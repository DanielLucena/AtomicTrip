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
        saveTrip(trip);
        String flightcheck = checkFlight(tripDetails);

        
    }

    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }
    public String checkFlight(TripDetailsDTO tripDetails) {
        // Lógica para verificar disponibilidade de voo
        System.out.println("Checking flight availability for: " + tripDetails);
        // Simulação de verificação de disponibilidade
        return "Flight available from " + tripDetails.getOrigem() + " to " + tripDetails.getDestino() + " on " + tripDetails.getDataIda();
    }
}
