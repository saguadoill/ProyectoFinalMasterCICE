package com.saguadopro.gestionusuarios.services.impl;

import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import java.util.List;

public interface UsuariosImp {
    
    Boolean crearUsuario(UsuarioDTO usuarioDTO);
    
    Boolean eliminarUsuario(Long idUsuario);
    
    Boolean modificarUsuario(UsuarioDTO usuario);

    List<UsuarioDTO> buscarUsuario(String username);
    
    List<UsuarioDTO> listarUsuarios();

    Boolean cambiarPasswd(Long idUsuario, String passwd);

    List<String> generarCampos(String nombre, String apellidos);

    List<PerfilDTO> listaPerfiles ();

    PerfilDTO buscarPerfil(String idPerfil);
}
