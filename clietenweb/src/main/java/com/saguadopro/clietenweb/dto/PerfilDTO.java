package com.saguadopro.clietenweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO que define los roles o perfiles de acceso a la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

    /**
     * ID o numero de identificación del perfil
     */
    private Integer idPerfil;

    /**
     * Nombre del perfil
     */
    private String nombrePerfil;
}
