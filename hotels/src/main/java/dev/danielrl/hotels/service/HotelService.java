package dev.danielrl.hotels.service;

import dev.danielrl.hotels.dto.ServiceResponse;
import dev.danielrl.hotels.model.HotelFullReservation;

public class HotelService {

    public ServiceResponse reserveHotel(HotelFullReservation hotel) {
        // Lógica para reservar um hotel
        return ServiceResponse.SUCCESS;
    }

    public ServiceResponse confirmHotel(HotelFullReservation hotel) {
        // Lógica para confirmar uma reserva de hotel
        return ServiceResponse.SUCCESS;
    }

    public ServiceResponse cancelHotel(HotelFullReservation hotel) {
        // Lógica para cancelar uma reserva de hotel
        return ServiceResponse.SUCCESS;
    }

}
