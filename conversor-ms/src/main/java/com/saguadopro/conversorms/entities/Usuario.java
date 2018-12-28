package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que define al usuario que trabajara con la aplicacion
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    /**
     * ID o identificacion del usuario
     */
    private Long idUsuario;

    /**
     * Nombre de usuario
     */
    private String username;

    /**
     * Contraseña del usuario
     */
    private String passwd;

    /**
     * Rol o perfil del usuario
     */
    private Perfil perfil;

    /**
     * Nombre real del usuario
     */
    private String nombre;

    /**
     * Apellidos reales del usuario
     */
    private String apellidos;

    /**
     * Indica si el usuario ha cambiado la contraseña inicial o no
     */
    private Boolean cambioPasswd;

    /**
     * Url o direccion en disco, en donde se encuentra la Foto del usuario
     */
    private String foto_url;
}