package com.saguadopro.gestionusuarios.services;

import com.saguadopro.gestionusuarios.entities.Perfil;
import com.saguadopro.gestionusuarios.entities.Usuario;
import com.saguadopro.gestionusuarios.feign.ConversorFeign;
import com.saguadopro.gestionusuarios.feign.FotosFeign;
import com.saguadopro.gestionusuarios.repositories.PerfilesRepo;
import com.saguadopro.gestionusuarios.repositories.UsuariosRepo;
import com.saguadopro.gestionusuarios.rest.dto.PerfilDTO;
import com.saguadopro.gestionusuarios.rest.dto.UsuarioDTO;
import com.saguadopro.gestionusuarios.services.impl.UsuariosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

/**
 * Servicio encargado de la gestion de los usuarios
 */
@Service
public class UsuariosService implements UsuariosImp {

    private final static Logger logger = Logger.getLogger(UsuariosService.class);

    @Autowired
    private UsuariosRepo usuariosRepo;

    @Autowired
    private PerfilesRepo perfilesRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    @Autowired
    private FotosFeign fotosFeign;

    @Autowired
    private GenerarUserPasswdService generarUserPasswdService;

    /**
     * Metodo que crea un usuario nuevo en la BBDD
     * @param usuarioDTO - Dto de tipo Usuario
     * @return - True si el usuario se ha creado. False si no ha sido posible
     */
    @Override
    public Boolean crearUsuario(UsuarioDTO usuarioDTO) {
      try {
          if (usuarioDTO != null) {
              System.out.println("entrando en donde la foto no esta vacia");
              Usuario usuarioBBDD = conversorFeign.usuarioDtoToEntity(usuarioDTO);
              usuariosRepo.save(usuarioBBDD);
              return true;
          } else {
              logger.error("El usuario no se ha podido crear. El usuarioDTO recibido es nulo");
              return false;
          }
      }catch (Exception e){
          logger.error("Ea habido un error al crear el usuario: "+e.getMessage());
          return false;
      }
    }

    /**
     * Metodo que elimina un usuario de la BBDD.
     * @param idUsuario - Id o numero de usuario
     * @return - True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarUsuario(Long idUsuario) {
        Usuario usuarioParaEliminar = usuariosRepo.findById(idUsuario).get();
        try {
            usuariosRepo.delete(usuarioParaEliminar);
            return true;
        } catch (Exception e) {
            logger.error("Usuario con id "+idUsuario+" no se ha podido eliminar:" + e.getMessage());
        }
        return false;
    }

    /**
     * Metodo que modifica los datos de un usuario en la BBDD
     * @param usuarioDtoModificado - Dto de tipo Usuario
     * @return - True; si se ha podido modificar. False; si no se ha podido modificar
     */
    @Override
    public Boolean modificarUsuario(UsuarioDTO usuarioDtoModificado) {
        try {
            Optional<Usuario> usuarioOriginal = usuariosRepo.findById(usuarioDtoModificado.getIdUsuario());
            if (usuarioOriginal.isPresent()) {

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
                    usuarioOriginal.get().setPerfil(conversorFeign.perfilEntityToDto(usuarioDtoModificado.getPerfil()));
                }
                if (!usuarioDtoModificado.getFoto().equals("")) {
                    usuarioOriginal.get().setFoto_url(fotosFeign.guardarFoto(usuarioOriginal.get().getUsername(),fotosFeign.decodificarFoto(usuarioDtoModificado.getFoto()) ));
                }
                usuariosRepo.save(usuarioOriginal.get());
                return true;
            } else {
                logger.error("El usuario a modificar no existe en la BBDD");
            }
        } catch (Exception e) {
            logger.error("No se ha podido modificar el usuario: "+e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Metodo que busca un usuario por nombre o id de usuario
     * @param username_id - Nombre de usuario o Id del usuario a buscar
     * @return - Lista de Dto tipo Usuario
     */
    @Override
    public List<UsuarioDTO> buscarUsuario(String username_id) {
        List<UsuarioDTO> usuariosDtoEncontrados = new ArrayList<>();
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        try {
            Long id = Long.parseLong(username_id);
            usuariosEncontrados.addAll(usuariosRepo.encontrarUsuarioById(id));
            for (Usuario usuario : usuariosEncontrados) {
                usuariosDtoEncontrados.add(conversorFeign.usuarioEntityToDto(usuario));
            }
            return usuariosDtoEncontrados;
        } catch (NumberFormatException e) {
            usuariosEncontrados.addAll(usuariosRepo.encontrarUsuario(username_id));
            for (Usuario usuario : usuariosEncontrados) {
                usuariosDtoEncontrados.add(conversorFeign.usuarioEntityToDto(usuario));
            }
            return usuariosDtoEncontrados;
        } catch (NullPointerException e) {
            logger.info("No hay ningun usuario con ese username/id");
            return null;
        } catch (Exception e){
            logger.error("No se ha podido buscar el usuario "+username_id+". Error: "+e.getMessage());
            return null;
        }
    }

    /**
     * Moetodo que devuelve una lista de todos los usuarios de la BBDD
     * @return Lista de Dto de tipo Usuario
     */
    @Override
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> listaUsuariosDto = new ArrayList<>();
        for (Usuario usuario : usuariosRepo.findAll()) {
            listaUsuariosDto.add(conversorFeign.usuarioEntityToDto(usuario));
        }
        return listaUsuariosDto;
    }

    /**
     * Metodo para cambiar el password del usuario. En caso de que sea la primera vez se añade true al campo CambioPasswd para indicar que ha cambiado el password inicial
     * @param idUsuario - Id o numero del usuario que cambia el password
     * @param passwd - Password nuevo
     * @return - True; si se ha cambiado el password. False; si no s eha podido cambiar
     */
    @Override
    public Boolean cambiarPasswd(Long idUsuario, String passwd) {
        if (idUsuario != null & !passwd.isEmpty()) {
            if (usuariosRepo.cambiarPasswd(passwd, idUsuario) > 0) {
                Usuario usuario = usuariosRepo.encontrarUsuarioById(idUsuario).get(0);
                usuario.setCambioPasswd(true);
                usuariosRepo.save(usuario);
                return true;
            } else {
                logger.error("No se ha podido cambiar el password");
                return false;
            }
        } else {
            logger.error("El id de usuario y/o password nuevo, son nulos o estan vacios");
            return false;
        }
    }

    /**
     * Metodo que genera los campos de username y password de forma automatica
     * @see {@link GenerarUserPasswdService}
     * @param nombre - Nombre del usuario
     * @param apellidos - Apellidos del usuario
     * @return Lista con dos Strings, uno con el username y otro con el password generados
     */
    public List<String> generarCampos(String nombre, String apellidos) {
        List<String> camposGenerados = new ArrayList<>();
        String usuarioGenerado = generarUserPasswdService.generarUsername(nombre, apellidos);
        camposGenerados.add(usuarioGenerado);
        camposGenerados.add(generarUserPasswdService.generarPasswd(usuarioGenerado));
        return camposGenerados;
    }

    /**
     * Metodo que devuelve una lista completa de los perfiles/roles que existen
     * @return Lista de Dto de tipo Perfil
     */
    @Override
    public List<PerfilDTO> listaPerfiles() {
        List<PerfilDTO> listaPerfilesDTO = new ArrayList<>();
        for (Perfil perfil:perfilesRepo.findAll()) {
            listaPerfilesDTO.add(conversorFeign.perfilEntityToDto(perfil));
        }
        return listaPerfilesDTO;
    }

    /**
     * Metodo apra buscar un perfil
     * @param idPerfil - Id o numero de perfil
     * @return - Dto de tipo Perfil
     */
    @Override
    public PerfilDTO buscarPerfil(String idPerfil) {
        return conversorFeign.perfilEntityToDto(perfilesRepo.findById(Integer.parseInt(idPerfil)).get());
    }
}
