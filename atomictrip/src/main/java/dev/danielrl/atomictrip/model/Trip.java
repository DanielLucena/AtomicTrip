package dev.danielrl.atomictrip.model;

import java.time.ZonedDateTime;
import java.util.UUID;


public class Trip {
    private UUID id;
    private Location destino;
    private Location origem;
    private ZonedDateTime dataIda;
    private ZonedDateTime dataVolta;
    private boolean isConfirmed;

    public Trip(Location origem, Location destino, ZonedDateTime dataVolta, ZonedDateTime dataIda) {
        this.id = UUID.randomUUID();
        this.destino = destino;
        this.origem = origem;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.isConfirmed = false;
    }

    public UUID getId() {
        return id;
    }

    public Location getDestino() {
        return destino;
    }

    public Location getOrigem() {
        return origem;
    }

    public ZonedDateTime getDataIda() {
        return dataIda;
    }

    public ZonedDateTime getDataVolta() {
        return dataVolta;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void confirmTrip() {
        this.isConfirmed = true;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", destino=" + destino +
                ", origem=" + origem +
                ", dataIda=" + dataIda +
                ", dataVolta=" + dataVolta +
                ", isConfirmed=" + isConfirmed +
                '}';
    }  

}
