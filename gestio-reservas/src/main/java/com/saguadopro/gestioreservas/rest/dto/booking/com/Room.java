package com.saguadopro.gestioreservas.rest.dto.booking.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO del Apartamento seleccionado en la reserva
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    /**
     * Fecha de entrada
     */
    private String arrival_date;

    /**
     * Codigo de la moneda utilizada para el pago
     */
    private String currencycode;

    /**
     * Fecha de salida
     */
    private String departure_date;

    /**
     * ID o numero del apartamento elegido
     */
    private Long id;

    /**
     * Regimen de comidas
     */
    private String meal_plan;

    /**
     * Numero de personas que ocuparan la estancia
     */
    private Integer numberofguests;

    /**
     * ID o numero de la reserva
     */
    private Long roomreservation_id;

    /**
     * Precio total
     */
    private Double totalprice;

}
