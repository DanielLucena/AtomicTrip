package dev.danielrl.flights.model;

import java.time.ZonedDateTime;
import java.util.UUID;


public class Flight {

    private Location origem;
    private Location destino;
    private ZonedDateTime data;

    public Flight(Location origem, Location destino, ZonedDateTime data) {
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }

    public UUID getId() {
        return UUID.nameUUIDFromBytes((origem.toString() + destino.toString() + data.toString()).getBytes());
    }

    public Location getOrigem() {
        return origem;
    }

    public Location getDestino() {
        return destino;
    }

    public ZonedDateTime getData() {
        return data;
    }

}
