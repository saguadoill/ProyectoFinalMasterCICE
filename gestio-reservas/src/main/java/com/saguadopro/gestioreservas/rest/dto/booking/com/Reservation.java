package com.saguadopro.gestioreservas.rest.dto.booking.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO de lo que seria una reserva de Booking
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    /**
     * Cliente que ha hecho la reserva
     */
    private Customer customer;

    /**
     * Fehca de realizacion de la reserva
     */
    private String date;

    /**
     * Id o numero identificativo de la reserva
     */
    private Long id;

    /**
     * Apartamento o habitacion de la reserva
     */
    private Room room;

    /**
     * Hora de realizacion de la reserva
     */
    private String time;

}
