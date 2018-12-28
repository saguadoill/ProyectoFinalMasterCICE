package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que define los roles o perfiles de acceso a la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {

    /**
     * ID o numero de identificación del perfil
     */
    private Integer idPerfil;

    /**
     * Nombre del perfil
     */
    private String nombrePerfil;

}
