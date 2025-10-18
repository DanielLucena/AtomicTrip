package dev.danielrl.flights.dto;

import java.time.ZonedDateTime;

import dev.danielrl.flights.model.Location;

public class TripDetailsDTO {
    private Location origem;
    private Location destino;
    private ZonedDateTime dataIda;
    private ZonedDateTime dataVolta;

    public TripDetailsDTO(String body) {
        try {
            String[] parts = body.split(",");
            this.origem = Location.valueOf(parts[0].split("=")[1]);
            this.destino = Location.valueOf(parts[1].split("=")[1]);
            this.dataIda = ZonedDateTime.parse(parts[2].split("=")[1]);
            this.dataVolta = ZonedDateTime.parse(parts[3].split("=")[1]);
        } catch (Exception e) {
            this.origem = null;
            this.destino = null;
            this.dataIda = null;
            this.dataVolta = null;
        }
    }

    public TripDetailsDTO(Location origem, Location destino, ZonedDateTime dataIda, ZonedDateTime dataVolta) {
        this.origem = origem;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
    }

    public TripDetailsDTO() {
    }

    public Location getOrigem() {
        return origem;
    }
    public Location getDestino() {
        return destino;
    }
    public ZonedDateTime getDataIda() {
        return dataIda;
    }
    public ZonedDateTime getDataVolta() {
        return dataVolta;
    }

    public boolean isEmpty() {
        return origem == null || destino == null || dataIda == null || dataVolta == null;
    }
}
