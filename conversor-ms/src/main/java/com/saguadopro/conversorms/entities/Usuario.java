package com.saguadopro.conversorms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private Long idUsuario;

    private String username;

    private String passwd;

    private Perfil perfil;

    private String nombre;

    private String apellidos;

    private Boolean cambioPasswd;

    private String foto_url;
}