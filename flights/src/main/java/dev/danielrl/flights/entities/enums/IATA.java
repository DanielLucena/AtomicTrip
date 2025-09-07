package dev.danielrl.flights.entities.enums;

//airport codes
public enum IATA {
    ATL("Hartsfield-Jackson Atlanta International Airport"),
    LAX("Los Angeles International Airport"),
    ORD("O'Hare International Airport"),
    DFW("Dallas/Fort Worth International Airport"),
    DEN("Denver International Airport"),
    JFK("John F. Kennedy International Airport"),
    SFO("San Francisco International Airport"),
    SEA("Seattle-Tacoma International Airport"),
    LAS("McCarran International Airport"),
    MCO("Orlando International Airport");

    private String airportName;

    IATA(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportName() {
        return airportName;
    }
}
