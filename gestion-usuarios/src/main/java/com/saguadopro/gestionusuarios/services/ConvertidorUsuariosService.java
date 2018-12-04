package com.saguadopro.gestionusuarios.services;

import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertidorUsuariosService {

    @Autowired
    GestionFotosService gestionFotosService;


    public UsuarioDTO usuarioToDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPerfil(usuario.getPerfil());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellidos(usuario.getApellidos());
        usuarioDTO.setPasswd(usuario.getPasswd());
        usuarioDTO.setCambioPasswd(usuario.getCambioPasswd());
        usuarioDTO.setFoto(gestionFotosService.codificarFoto(usuario.getFoto_url()));
        return usuarioDTO;
    }

    public Usuario usuarioDtoToUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPerfil(usuarioDTO.getPerfil());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setPasswd(usuarioDTO.getPasswd());
        if (!usuarioDTO.getFoto().isEmpty() || !usuarioDTO.getFoto().equals("")){
            usuario.setFoto_url(gestionFotosService.guardarFoto(usuarioDTO.getUsername(),
                    gestionFotosService.decodificarFoto(usuarioDTO.getFoto())));
        }else {
           usuario.setFoto_url("src/main/resources/fotos/logo_user_off.png");
        }

        return usuario;
    }
}
