package dev.danielrl.flights.service;

import dev.danielrl.flights.dto.ServiceResponse;
import dev.danielrl.flights.model.Flight;
import dev.danielrl.flights.repositoy.FlightRepository;

public class FlightService {

    private FlightRepository flightRepository;

    public FlightService() {
        flightRepository = new FlightRepository();
    }

    public ServiceResponse reserveFlight(Flight flight) {
        boolean result = flightRepository.reserveSeat(flight);
        if (result) {
            return ServiceResponse.SUCCESS;
        } else {
            return ServiceResponse.FAILURE;
        }
    }

    public ServiceResponse confirmFlight(Flight flight) {
        boolean result = flightRepository.confirmSeat(flight);
        if (result) {
            return ServiceResponse.SUCCESS;
        } else {
            return ServiceResponse.FAILURE;
        }
    }

    public ServiceResponse cancelFlight(Flight flight) {
        boolean result = flightRepository.cancelReservedSeat(flight);
        if (result) {
            return ServiceResponse.SUCCESS;
        } else {
            return ServiceResponse.FAILURE;
        }
    }
}
