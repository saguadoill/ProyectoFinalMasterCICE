package com.saguadopro.gestioreservas.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * POJO que define una reserva.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    /**
     * ID o numero identificativo de la reserva
     */
    private Long idReserva;

    /**
     * Nombre del cliente que ha realizado la reserva.
     */
    private String cliente;

    /**
     * Fecha de entrada de la reserva
     */
    private LocalDate fechaEntrada;

    /**
     * Fecha de salida de la reserva
     */
    private LocalDate fechaSalida;

    /**
     * Numero de personas que ocuparan el apartamento
     */
    private Integer capacidad;

    /**
     * ID o numero identificativo del apartamento elegido por el cliente
     */
    private Long idApartamento;

    /**
     * Precio total de la reserva
     */
    private Double precioTotal;

    /**
     * Indica si la reserva lleva o no parking incluido
     */
    private Boolean tieneParking;

    /**
     * Indica si el apartamento seleccionado esta ya asignado u ocupado
     */
    private Boolean estaAsignada;
}
