package dev.danielrl.atomictrip.service;

import java.security.Provider.Service;

import dev.danielrl.atomictrip.dto.ServiceResponse;
import dev.danielrl.atomictrip.dto.TripDetailsDTO;
import dev.danielrl.atomictrip.model.Trip;
import dev.danielrl.atomictrip.repository.TripRepository;

public class TripService {

    private TripRepository tripRepository;

    private FlightsClientService flightsClientService = new FlightsClientService(8010);

    private HotelsClientService hotelsClientService = new HotelsClientService(8020);

    public TripService() {
        this.tripRepository = new TripRepository();
    }

    public String bookTrip(TripDetailsDTO tripDetails) {
        // Lógica para reservar uma viagem
        System.out.println("Booking trip with details: " + tripDetails);
        Trip trip = new Trip(tripDetails.getOrigem(), tripDetails.getDestino(), tripDetails.getDataVolta(), tripDetails.getDataIda());
        saveTrip(trip);
        ServiceResponse reservaflight = flightsClientService.reserveFlights(tripDetails);
        ServiceResponse reservahotel = hotelsClientService.reserveHotelDays(tripDetails);
        if (reservaflight == ServiceResponse.SUCCESS && reservahotel == ServiceResponse.SUCCESS) {
            ServiceResponse confirmaflight = flightsClientService.confirmFlights(tripDetails);
            ServiceResponse confirmahotel = hotelsClientService.confirmHotelDays(tripDetails);

            if (confirmaflight == ServiceResponse.SUCCESS && confirmahotel == ServiceResponse.SUCCESS) {
                trip.confirmTrip();
                tripRepository.save(trip);
                return "Trip booked successfully.";
            } else {
                flightsClientService.cancelFlights(tripDetails);
                hotelsClientService.cancelHotelDays(tripDetails);
                return "Unexpected error during trip confirmation.";
            }
        } else {
            flightsClientService.cancelFlights(tripDetails);
            hotelsClientService.cancelHotelDays(tripDetails);
            return "Failed to book trip. Hotel situation: " + reservahotel + ", Flight situation: " + reservaflight;
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
