package com.saguadopro.clietenweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropietarioDTO {

    private Integer idPropietario;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String email;

}
