package dev.danielrl.flights.model;

import java.util.UUID;

public class FlightReservation {

    private UUID flightId;
    private int confirmedSeats;
    private int reservedSeats;

    private static final int MAX_SEATS = 100;

    public FlightReservation(UUID flightId) {
        this.flightId = flightId;
        this.confirmedSeats = 0;
        this.reservedSeats = 0;
    }

    public UUID getFlightId() {
        return this.flightId;
    }

    public int getConfirmedSeats() {
        return this.confirmedSeats;
    }

    public int getReservedSeats() {
        return this.reservedSeats;
    }

    public boolean reserveSeat() {
        if (this.reservedSeats + this.confirmedSeats >= MAX_SEATS) {
            return false;
        }
        this.reservedSeats += 1;
        return true;
    }

    public boolean confirmSeat() {
        if (this.reservedSeats > 0) {
            this.reservedSeats -= 1;
            this.confirmedSeats += 1;
            return true;
        }
        return false;
    }

    public boolean cancelReservedSeat() {
        if (this.reservedSeats > 0) {
            this.reservedSeats -= 1;
            return true;
        }
        return false;
    }
}
