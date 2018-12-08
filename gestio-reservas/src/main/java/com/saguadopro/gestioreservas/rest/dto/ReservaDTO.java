package com.saguadopro.gestioreservas.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Long idReserva;

    private String cliente;

    private LocalDate fechaEntrada;

    private LocalDate fechaSalida;

    private Integer capacidad;

    private Long idApartamento;

    private Double precioTotal;

    private Boolean parking;
}
