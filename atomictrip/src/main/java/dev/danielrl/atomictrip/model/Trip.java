package dev.danielrl.atomictrip.model;

import java.util.Date;
import java.util.UUID;


public class Trip {
    private UUID id;
    private Location destino;
    private Location origem;
    private Date dataIda;
    private Date dataVolta;

    public Trip(Location origem, Location destino, Date dataVolta, Date dataIda) {
        this.id = UUID.randomUUID();
        this.destino = destino;
        this.origem = origem;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
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

    public Date getDataIda() {
        return dataIda;
    }

    public Date getDataVolta() {
        return dataVolta;
    }
}
