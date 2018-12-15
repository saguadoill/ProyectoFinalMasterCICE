package com.saguadopro.clietenweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long idUsuario;

    private String username;

    private String passwd;

    private PerfilDTO perfil;

    private String nombre;

    private String apellidos;

    private Boolean cambioPasswd;

    private String foto;
}
