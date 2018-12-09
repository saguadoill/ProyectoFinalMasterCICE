package com.saguadopro.gestionapartamentos.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartamentos")
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartamento", nullable = false, unique = true)
    private Long idApartamento;

    @OneToOne
    @JoinColumn(name = "capacidad")
    private Capacidad capacidad;
//
//    @Column(name = "capacidad")
//    private Integer capacidad;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "piso")
    private String piso;

    @Column(name = "puerta")
    private String puerta;

    @Column(name = "foto_url")
    private String foto_url;
//
//    @Column(name = "disponible")
//    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_huesped")
    private Huesped huesped;

    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Propietario propietario;
}

