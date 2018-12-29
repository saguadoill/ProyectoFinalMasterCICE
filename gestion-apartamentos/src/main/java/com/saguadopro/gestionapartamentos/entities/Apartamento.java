package com.saguadopro.gestionapartamentos.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entidad que define un  Apartamento o inmueble.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartamentos")
public class Apartamento {

    /**
     * Id o numero de identificación del Apartamento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartamento", nullable = false, unique = true)
    private Long idApartamento;

    /**
     * Numero de personas que pueden ocupar el inmueble
     */
    @OneToOne
    @JoinColumn(name = "capacidad")
    private Capacidad capacidad;

    /**
     * Direccion del inmueble
     */
    @Column(name = "direccion")
    private String direccion;

    /**
     * Piso o planta del inmueble
     */
    @Column(name = "piso")
    private String piso;

    /**
     * Puerta y/o numero del inmueble
     */
    @Column(name = "puerta")
    private String puerta;

    /**
     * Url o direccion de la Foto del inmueble que lo identifique si es necesario.
     */
    @Column(name = "foto_url")
    private String foto_url;

    /**
     * Huesped que actualmente ocupa el inmueble. Puede ser null
     */
    @ManyToOne
    @JoinColumn(name = "id_huesped")
    private Huesped huesped;

    /**
     * Propietario o dueño del inmueble.
     */
    @ManyToOne
    @JoinColumn(name = "id_propietario")
    private Propietario propietario;
}

