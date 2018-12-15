package com.saguadopro.gestionusuarios.services;

import com.saguadopro.gestionusuarios.conversor.Conversor;
import com.saguadopro.gestionusuarios.entities.Perfil;
import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.repositories.PerfilesRepo;
import com.saguadopro.gestionusuarios.repositories.UsuariosRepo;
import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import com.saguadopro.gestionusuarios.services.impl.UsuariosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.apache.log4j.Logger;


@Service
public class UsuariosService implements UsuariosImp {

//    private final static Logger logger = Logger.getLogger(GestionUsuariosService.class);

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Autowired
    private PerfilesRepo perfilesRepo;

    @Autowired
    private GestionFotosService gestionFotosService;

    @Autowired
    private Conversor conversor;

    @Autowired
    private GenerarUserPasswdService generarUserPasswdService;

    @Override
    public Boolean crearUsuario(UsuarioDTO usuarioDTO) {
        String foto_url;
        System.out.println(usuarioDTO);
        if (usuarioDTO != null) {
            System.out.println("entrando en donde la foto no esta vacia");
            Usuario usuarioBBDD = conversor.usuarioDtoToUsuario(usuarioDTO);
            usuariosRepo.save(usuarioBBDD);
            return true;
        } else {
//            logger.error("El usuario no se ha podido crear");
            return false;
        }
    }
    @Override
    public Boolean eliminarUsuario(Long idUsuario) {
        Usuario usuarioParaEliminar = usuariosRepo.findById(idUsuario).get();
        try {
            usuariosRepo.delete(usuarioParaEliminar);
            return true;
        } catch (Exception e) {
//            logger.error("Usuario no se ha podido eliminar:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean modificarUsuario(UsuarioDTO usuarioDtoModificado) {
        try {
            Optional<Usuario> usuarioOriginal = usuariosRepo.findById(usuarioDtoModificado.getIdUsuario());
            if (usuarioOriginal.isPresent()) {
                BufferedImage fotoUsuarioModificada = gestionFotosService.decodificarFoto(usuarioDtoModificado.getFoto());

                if (!usuarioOriginal.get().getUsername().equals(usuarioDtoModificado.getUsername())) {
                    usuarioOriginal.get().setUsername(usuarioDtoModificado.getUsername());
                }
                if (!usuarioOriginal.get().getNombre().equals(usuarioDtoModificado.getNombre())) {
                    usuarioOriginal.get().setNombre(usuarioDtoModificado.getNombre());
                }
                if (!usuarioOriginal.get().getApellidos().equals(usuarioDtoModificado.getApellidos())) {
                    usuarioOriginal.get().setApellidos(usuarioDtoModificado.getApellidos());
                }
                if (!usuarioOriginal.get().getPerfil().getNombrePerfil().equals(usuarioDtoModificado.getPerfil().getNombrePerfil())) {
                    usuarioOriginal.get().setPerfil(conversor.perfilDtoToPerfil(usuarioDtoModificado.getPerfil()));
                }
                if (!usuarioDtoModificado.getFoto().equals("")) {
                    usuarioOriginal.get().setFoto_url(gestionFotosService.guardarFoto(usuarioOriginal.get().getUsername(), fotoUsuarioModificada));
                }
                usuariosRepo.save(usuarioOriginal.get());
                return true;
            } else {
                System.out.println("log4j: el usuario no se ha podido modificar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UsuarioDTO> buscarUsuario(String username_id) {
        List<UsuarioDTO> usuariosDtoEncontrados = new ArrayList<>();
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        try {
            Long id = Long.parseLong(username_id);
            usuariosEncontrados.addAll(usuariosRepo.encontrarUsuarioById(id));
            for (Usuario usuario : usuariosEncontrados) {
                usuariosDtoEncontrados.add(conversor.usuarioToDto(usuario));
            }
            return usuariosDtoEncontrados;
        } catch (NumberFormatException e) {
            usuariosEncontrados.addAll(usuariosRepo.encontrarUsuario(username_id));
            for (Usuario usuario : usuariosEncontrados) {
                usuariosDtoEncontrados.add(conversor.usuarioToDto(usuario));
            }
            return usuariosDtoEncontrados;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> listaUsuariosDto = new ArrayList<>();
        for (Usuario usuario : usuariosRepo.findAll()) {
            listaUsuariosDto.add(conversor.usuarioToDto(usuario));
        }
        return listaUsuariosDto;
    }

//    @Override
//    public Boolean noPasswdInicial(Long idUsuario) {
//        if (idUsuario != null & !buscarUsuario(String.valueOf(idUsuario)).isEmpty()) {
//            return usuariosRepo.verificarCambioPasswd(idUsuario);
//        } else {
//            return false;
//            //loj4j indicar que el id usuario es null
//        }
//
//    }

    @Override
    public Boolean cambiarPasswd(Long idUsuario, String passwd) {
        if (idUsuario != null & !passwd.isEmpty()) {
            if (usuariosRepo.cambiarPasswd(passwd, idUsuario) > 0) {
                Usuario usuario = usuariosRepo.encontrarUsuarioById(idUsuario).get(0);
                usuario.setCambioPasswd(true);
                usuariosRepo.save(usuario);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public List<String> generarCampos(String nombre, String apellidos) {
        List<String> camposGenerados = new ArrayList<>();
        String usuarioGenerado = generarUserPasswdService.generarUsername(nombre, apellidos);
        System.out.println(usuarioGenerado);
        camposGenerados.add(usuarioGenerado);
        camposGenerados.add(generarUserPasswdService.generarPasswd(usuarioGenerado));
        System.out.println(generarUserPasswdService.generarPasswd(usuarioGenerado));
        return camposGenerados;
    }

    @Override
    public List<PerfilDTO> listaPerfiles() {
        List<PerfilDTO> listaPerfilesDTO = new ArrayList<>();
        for (Perfil perfil:perfilesRepo.findAll()) {
            listaPerfilesDTO.add(conversor.perfilToDto(perfil));
        }
        return listaPerfilesDTO;
    }
}
