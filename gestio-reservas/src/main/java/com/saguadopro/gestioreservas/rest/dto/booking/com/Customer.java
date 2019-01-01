package com.saguadopro.gestioreservas.rest.dto.booking.com;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO de lo que seria un Cliente de una reserba Booking
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    /**
     * Nombre del cliente
     */
    private String first_name;

    /**
     * Apellidos del cliente
     */
    private String last_name;

}
