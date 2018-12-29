package com.saguadopro.gestionapartamentos.rest.dto;

import lombok.Data;

/**
 * POJO que defina a un huesped del apartamento.
 */
@Data
public class HuespedDTO {

    /**
     * Id o numero identificattivo del huesped
     */
    private Long idHuesped;

    /**
     * Nombre del huesped
     */
    private String nombre;

    /**
     * Apellidos del huesped
     */
    private String apellidos;

    /**
     * Numero de telefono de contacto del huesped
     */
    private String telefono;

    /**
     * Correo electronico del huesped
     */
    private String email;

    /**
     * DNI del huesped por seguridad.
     */
    private String dni;

}
