package com.saguadopro.gestionusuarios.conversor;

import com.saguadopro.gestionusuarios.entities.Perfil;
import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import com.saguadopro.gestionusuarios.services.GestionFotosService;
import com.saguadopro.gestionusuarios.services.UsuariosService;
import com.saguadopro.gestionusuarios.services.impl.UsuariosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Conversor {

    @Autowired
    GestionFotosService gestionFotosService;

    @Autowired
    UsuariosImp usuariosImp;


    public UsuarioDTO usuarioToDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPerfil(perfilToDto(usuario.getPerfil()));
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
        usuario.setPerfil(perfilDtoToPerfil(usuarioDTO.getPerfil()));
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

    public Perfil perfilDtoToPerfil(PerfilDTO perfilDTO){
        Perfil perfil = new Perfil();
        perfil.setIdPerfil(perfilDTO.getIdPerfil());
        perfil.setNombrePerfil(perfilDTO.getNombrePerfil());
        return perfil;
    }

    public PerfilDTO perfilToDto(Perfil perfil){
        PerfilDTO perfilDTO = new PerfilDTO();
        perfilDTO.setIdPerfil(perfil.getIdPerfil());
        perfilDTO.setNombrePerfil(perfil.getNombrePerfil());
        return perfilDTO;
    }
}
