package dev.danielrl.flights.repositoy;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import dev.danielrl.flights.model.Flight;
import dev.danielrl.flights.model.FlightReservation;

public class FlightRepository {

    private ConcurrentHashMap<UUID, FlightReservation> flights = new ConcurrentHashMap<UUID, FlightReservation>();

    public FlightReservation getReservation(Flight flight) {
        FlightReservation reservation = flights.get(flight.getId());
        if (reservation == null) {
            System.out.println("Creating new reservation for flight: " + flight.getId() + ", " + flight.getOrigem() + "->" + flight.getDestino() + " on " + flight.getData());
            reservation = new FlightReservation(flight.getId());
            flights.put(flight.getId(), reservation);
        }
        return reservation;
    }

    public boolean reserveSeat(Flight flight) {
        FlightReservation reservation = getReservation(flight);
        boolean result = reservation.reserveSeat();
        flights.put(flight.getId(), reservation);
        System.out.println("Confirmed - " + reservation.getConfirmedSeats() + ", Reserved - " + reservation.getReservedSeats() + " for flight: " + flight.getId());
        return result;
    }

    public boolean confirmSeat(Flight flight) {
        FlightReservation reservation = getReservation(flight);
        boolean result = reservation.confirmSeat();
        flights.put(flight.getId(), reservation);
        System.out.println("Confirmed - " + reservation.getConfirmedSeats() + ", Reserved - " + reservation.getReservedSeats() + " for flight: " + flight.getId());
        return result;
    }

    public boolean cancelReservedSeat(Flight flight) {
        FlightReservation reservation = getReservation(flight);
        boolean result = reservation.cancelReservedSeat();
        flights.put(flight.getId(), reservation);
        System.out.println("Confirmed - " + reservation.getConfirmedSeats() + ", Reserved - " + reservation.getReservedSeats() + " for flight: " + flight.getId());
        return result;
    }
}