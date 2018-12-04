package com.saguadopro.gestionapartamentos.rest.dto;

import lombok.Data;

@Data
public class PropietarioDTO {

    private Integer idPropietario;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String email;

}
