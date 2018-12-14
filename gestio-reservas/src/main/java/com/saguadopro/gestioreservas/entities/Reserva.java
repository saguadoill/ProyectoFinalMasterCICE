package com.saguadopro.gestioreservas.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @Column(name = "id_reservas", nullable = false, unique = true)
    private Long idReserva;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "numero_personas")
    private Integer numero_personas;

    @Column(name = "id_apartamento")
    private Long idApartamento;

    @Column(name = "precio_total")
    private Double precioTotal;

    @Column(name = "parking")
    private Boolean tieneParking;

    @Column(name = "asignada")
    private Boolean estaAsignada;

}
