package com.saguadopro.conversorms.rest.dto;

import lombok.Data;

/**
 * POJO que define al propietario de un apartamento o inmueble
 */
@Data
public class PropietarioDTO {

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
