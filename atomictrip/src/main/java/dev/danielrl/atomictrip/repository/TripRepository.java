package dev.danielrl.atomictrip.repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import dev.danielrl.atomictrip.model.Trip;

public class TripRepository {
    private ConcurrentHashMap<UUID,Trip> trips = new ConcurrentHashMap<UUID, Trip>();

    public Trip save(Trip trip) {
        if (trip.getId() == null) {
            trip.setId(UUID.randomUUID());
        }
        trips.put(trip.getId(), trip);
        return trip;
    }

    public Trip findById(UUID id) {
        return trips.get(id);
    }

    public Trip confirmTrip(UUID id) {
        Trip trip = trips.get(id);
        if (trip != null) {
            trip.confirmTrip();
            trips.put(id, trip);
        }
        return trip;
    }


}
