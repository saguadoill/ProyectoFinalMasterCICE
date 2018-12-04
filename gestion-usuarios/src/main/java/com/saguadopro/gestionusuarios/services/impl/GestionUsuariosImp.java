package com.saguadopro.gestionusuarios.services.impl;

import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;

import java.awt.*;
import java.util.List;

public interface GestionUsuariosImp {
    
    Boolean crearUsuario(UsuarioDTO usuarioDTO);
    
    Boolean eliminarUsuario(Long idUsuario);
    
    Boolean modificarUsuario(UsuarioDTO usuario);

    List<UsuarioDTO> buscarUsuario(String username);
    
    List<UsuarioDTO> listarUsuarios();

    Boolean noPasswdInicial(Long idUsuario);

    Boolean cambiarPasswd(Long idUsuario, String passwd);

    String subirFoto(Image foto_usuario, Long idUsuario);

    Boolean descargarFoto(String foto_url,  Long idUsuario);
}
