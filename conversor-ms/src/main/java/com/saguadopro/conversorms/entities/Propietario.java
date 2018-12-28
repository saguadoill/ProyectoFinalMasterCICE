package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que define al propietario de un apartamento o inmueble
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propietario {

    /**
     * ID o numero de identificacion del propietario
     */
    private Integer idPropietario;

    /**
     * Nombre del propietario
     */
    private String nombre;

    /**
     * Apellidos del propietario
     */
    private String apellidos;

    /**
     * Numero de telefono de contacto del propietario
     */
    private String telefono;

    /**
     * Correo electronico del propietario
     */
    private String email;

}
