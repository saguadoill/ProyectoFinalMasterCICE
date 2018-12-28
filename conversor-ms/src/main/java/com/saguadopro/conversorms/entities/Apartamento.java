package com.saguadopro.conversorms.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que define un  Apartamento o inmueble.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {

    /**
     * Id o numero de identificación del Apartamento
     */
    private Long idApartamento;

    /**
     * Numero de personas que pueden ocupar el inmueble
     */
    private Capacidad capacidad;

    /**
     * Direccion del inmueble
     */
    private String direccion;

    /**
     * Piso o planta del inmueble
     */
    private String piso;

    /**
     * Puerta y/o numero del inmueble
     */
    private String puerta;

    /**
     * Foto del inmueble que lo identifique si es necesario.
     */
    private String foto_url;

    /**
     * Huesped que actualmente ocupa el inmueble. Puede ser null
     */
    private Huesped huesped;

    /**
     * Propietario o dueño del inmueble.
     */
    private Propietario propietario;
}

