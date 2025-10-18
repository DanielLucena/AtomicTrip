package dev.danielrl.flights.service;

import dev.danielrl.flights.dto.FlightResponse;
import dev.danielrl.flights.model.Flight;
import dev.danielrl.flights.repositoy.FlightRepository;

public class FlightService {

    private FlightRepository flightRepository;

    public FlightService() {
        flightRepository = new FlightRepository();
    }

    public FlightResponse reserveFlight(Flight flight) {
        boolean result = flightRepository.reserveSeat(flight);
        if (result) {
            return FlightResponse.SUCCESS;
        } else {
            return FlightResponse.FAILURE;
        }
    }

    public FlightResponse confirmFlight(Flight flight) {
        boolean result = flightRepository.confirmSeat(flight);
        if (result) {
            return FlightResponse.SUCCESS;
        } else {
            return FlightResponse.FAILURE;
        }
    }

    public FlightResponse cancelFlight(Flight flight) {
        boolean result = flightRepository.cancelReservedSeat(flight);
        if (result) {
            return FlightResponse.SUCCESS;
        } else {
            return FlightResponse.FAILURE;
        }
    }
}
