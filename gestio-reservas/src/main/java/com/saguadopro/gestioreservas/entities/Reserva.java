package com.saguadopro.gestioreservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Entidad que define una reserva.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    /**
     * ID o numero identificativo de la reserva
     */
    @Id
    @Column(name = "id_reservas", nullable = false, unique = true)
    private Long idReserva;

    /**
     * Nombre del cliente que ha realizado la reserva.
     */
    @Column(name = "cliente")
    private String cliente;

    /**
     * Fecha de entrada de la reserva
     */
    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    /**
     * Fecha de salida de la reserva
     */
    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    /**
     * Numero de personas que ocuparan el apartamento
     */
    @Column(name = "numero_personas")
    private Integer numero_personas;

    /**
     * ID o numero identificativo del apartamento elegido por el cliente
     */
    @Column(name = "id_apartamento")
    private Long idApartamento;

    /**
     * Precio total de la reserva
     */
    @Column(name = "precio_total")
    private Double precioTotal;

    /**
     * Indica si la reserva lleva o no parking incluido
     */
    @Column(name = "parking")
    private Boolean tieneParking;

    /**
     * Indica si el apartamento seleccionado esta ya asignado u ocupado
     */
    @Column(name = "asignada")
    private Boolean estaAsignada;

}
