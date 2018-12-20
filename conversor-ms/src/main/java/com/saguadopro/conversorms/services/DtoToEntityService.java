package com.saguadopro.conversorms.services;

import com.saguadopro.conversorms.entities.Perfil;
import com.saguadopro.conversorms.entities.Usuario;
import com.saguadopro.conversorms.feign.FotosFeign;
import com.saguadopro.conversorms.rest.dto.PerfilDTO;
import com.saguadopro.conversorms.rest.dto.UsuarioDTO;
import com.saguadopro.conversorms.services.impl.DtoToEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class DtoToEntityService implements DtoToEntityImpl {

    @Autowired
    private FotosFeign fotosFeign;


    public  Usuario usuarioDtoToEntity(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        System.out.println("Foto usuarioDTO: "+usuarioDTO.getFoto());
        usuario.setIdUsuario(usuarioDTO.getIdUsuario());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPerfil(perfilDtoToPerfil(usuarioDTO.getPerfil()));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setPasswd(usuarioDTO.getPasswd());
        if (!usuarioDTO.getFoto().isEmpty() || !usuarioDTO.getFoto().equals("")){
            usuario.setFoto_url(fotosFeign.guardarFoto(usuarioDTO.getUsername(),
                    fotosFeign.decodificarFoto(usuarioDTO.getFoto())));
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

}
