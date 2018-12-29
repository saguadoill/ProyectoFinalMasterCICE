package com.saguadopro.gestionapartamentos.rest.dto;

import lombok.Data;

/**
 * POJO que define un  Apartamento.
 */
@Data
public class ApartamentoDTO {

    /**
     * Id o numero de identificación del Apartamento
     */
    private Long idApartamento;

    /**
     * Numero de personas que pueden ocupar el inmueble
     */
    private CapacidadDTO capacidad;

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
    private String foto;

    /**
     * Huesped que actualmente ocupa el inmueble. Puede ser null
     */
    private HuespedDTO huesped;

    /**
     * Propietario o dueño del inmueble.
     */
    private PropietarioDTO propietario;
}
