package dev.danielrl.atomictrip.dto;

import java.time.ZonedDateTime;

import dev.danielrl.atomictrip.model.Location;

public record TrechoDTO(ZonedDateTime data, Location origem, Location destino) {
}
