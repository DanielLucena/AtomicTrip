package dev.danielrl.hotels.model;

import java.util.UUID;

public class HotelDayOccupation {

    private UUID hotelId;
    private int confirmedRooms;
    private int reservedRooms;

    private static final int MAX_ROOMS = 100;

    public HotelDayOccupation(UUID hotelId) {
        this.hotelId = hotelId;
        this.confirmedRooms = 0;
        this.reservedRooms = 0;
    }

    public UUID getHotelId() {
        return this.hotelId;
    }

    public int getConfirmedRooms() {
        return this.confirmedRooms;
    }

    public int getReservedRooms() {
        return this.reservedRooms;
    }

    public boolean confirmRoom() {
        if (this.reservedRooms > 0) {
            this.reservedRooms -= 1;
            this.confirmedRooms += 1;
            return true;
        }
        return false;
    }

    public boolean reserveRoom() {
        if (this.reservedRooms + this.confirmedRooms >= MAX_ROOMS) {
            return false;
        }
        this.reservedRooms += 1;
        return true;
    }

    public boolean cancelReservedRoom() {
        if (this.reservedRooms > 0) {
            this.reservedRooms -= 1;
            return true;
        }
        return false;
    }

}
