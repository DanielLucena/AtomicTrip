package dev.danielrl.atomictrip.service;

import java.security.Provider.Service;

import dev.danielrl.atomictrip.dto.ServiceResponse;
import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Trip;
import dev.danielrl.atomictrip.repository.TripRepository;

public class TripService {

    private TripRepository tripRepository;

    private FlightsClientService flightsClientService = new FlightsClientService(8010);

    public TripService() {
        this.tripRepository = new TripRepository();
    }

    public void bookTrip(TripDetailsDTO tripDetails) {
        // Lógica para reservar uma viagem
        System.out.println("Booking trip with details: " + tripDetails);
        Trip trip = new Trip(tripDetails.getOrigem(), tripDetails.getDestino(), tripDetails.getDataVolta(), tripDetails.getDataIda());
        saveTrip(trip);
        ServiceResponse reservaflight = flightsClientService.reserveFlights(tripDetails);
        if (reservaflight == ServiceResponse.SUCCESS) {
            ServiceResponse confirmaflight = flightsClientService.confirmFlights(tripDetails);


            if (confirmaflight == ServiceResponse.SUCCESS) {
                trip.confirmTrip();
                tripRepository.save(trip);
            } else {
                flightsClientService.cancelFlights(tripDetails);
            }
        } else {
            flightsClientService.cancelFlights(tripDetails);
        }

        
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
