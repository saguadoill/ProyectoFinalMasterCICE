package com.saguadopro.conversorms.services;

import com.saguadopro.conversorms.entities.Perfil;
import com.saguadopro.conversorms.entities.Usuario;
import com.saguadopro.conversorms.feign.FotosFeign;
import com.saguadopro.conversorms.rest.dto.PerfilDTO;
import com.saguadopro.conversorms.rest.dto.UsuarioDTO;
import com.saguadopro.conversorms.services.impl.EntityToDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityToDtoService implements EntityToDtoImpl {

    @Autowired
    FotosFeign fotosFeign;



    public UsuarioDTO usuarioEntityToDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPerfil(perfilEntityToDto(usuario.getPerfil()));
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellidos(usuario.getApellidos());
        usuarioDTO.setPasswd(usuario.getPasswd());
        usuarioDTO.setCambioPasswd(usuario.getCambioPasswd());
        usuarioDTO.setFoto(fotosFeign.codificarFoto(usuario.getFoto_url()));
        return usuarioDTO;
    }

    public PerfilDTO perfilEntityToDto(Perfil perfil){
        PerfilDTO perfilDTO = new PerfilDTO();
        perfilDTO.setIdPerfil(perfil.getIdPerfil());
        perfilDTO.setNombrePerfil(perfil.getNombrePerfil());
        return perfilDTO;
    }
}
