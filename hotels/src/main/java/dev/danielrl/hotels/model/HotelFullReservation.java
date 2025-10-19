package dev.danielrl.hotels.model;

import java.time.ZonedDateTime;

public class HotelFullReservation {

    private Location location;
    private ZonedDateTime dataIda;
    private ZonedDateTime dataVolta;

    public HotelFullReservation(Location location, ZonedDateTime dataIda, ZonedDateTime dataVolta) {
        this.location = location;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
    }
}
