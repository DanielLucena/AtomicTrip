package dev.danielrl.flights.entities;

import java.util.Date;

import dev.danielrl.flights.entities.enums.IATA;

public class Flight {
    private IATA origin;
    private IATA destiny;
    private String flightNumber;
    private Date departureDay;
    private Date arrivalDay;
}
