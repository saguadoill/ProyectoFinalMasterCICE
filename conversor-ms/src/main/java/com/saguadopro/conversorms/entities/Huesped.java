package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que defina a un huesped del apartamento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Huesped {

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
