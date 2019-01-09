package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.PerfilDTO;
import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * Servicio encargado de las páginas para la gestion de los usuarios
 */
@Service
public class UsuariosWebServices {

    private static Logger logger = Logger.getLogger(UsuariosWebServices.class);

    @Autowired
    private UsuariosFeign usuariosFeign;

    @Autowired
    private InicioWebService inicioWebService;


    /**
     * Metodo para la creación de un usuario nueco en la BBDD
     * @param usuarioDTO - Usuario nuevo
     * @param idPerfil - Role o perfil que tendrá el usuario
     * @param file - Foto del usuario
     * @param principal - Usuario logado
     * @return - Vista de la página de creacion de usuarios limpia para añadir un nuevo usuario
     */
    public ModelAndView crearUsuario(UsuarioDTO usuarioDTO, String idPerfil,MultipartFile file, Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("pagina","pages/usuarios/nuevo");
        try{
            usuarioDTO.setPerfil(usuariosFeign.buscarPerfil(idPerfil));
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuarioDTO.setPasswd(passwordEncoder.encode(usuarioDTO.getPasswd()));
            byte[] fotoForm = file.getBytes();
            usuarioDTO.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            usuarioDTO.setCambioPasswd(false);
            if (usuariosFeign.crearUsuarioFeign(usuarioDTO) == HttpStatus.CREATED) {
                logger.info("Usuario "+usuarioDTO.getUsername()+" creado de forma correcta");
            } else {
                logger.error("El usuario "+usuarioDTO.getUsername()+" no se ha podido crear");
            }
        } catch (IOException e) {
            logger.error("Error al convertir la foto o imagen en bytes: " + e.getMessage());
        } catch (Exception e){
           logger.error("No se ha podido crear el usuario. Comprobar UsuariosMicroservice para mas info: "+e.getMessage());
        }
        return vista;
    }

    /**
     * Método encargado de generar un nombre de usuario y una contraseña inicial.
     * @param nombre - Nombre del usuario
     * @param apellidos - Apellidos del usuario
     * @return - Lista de Strings con el username y el passwd
     */
    public ResponseEntity<List<String>> generarUserPass(String nombre, String apellidos){
        System.out.println("Llega al service: "+nombre+" "+apellidos);
        ResponseEntity<List<String>> campos;
        try {
            List<String> lista = usuariosFeign.generarUserPass(nombre, apellidos);
            campos =  new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (NullPointerException e) {
            campos =  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            logger.error("No se han podido generar los campos. Comprobar UsuariosMicroservice para mas info: "+e.getMessage());
            campos =  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return campos;
    }

    /**
     * Método que elimina un usuario de la BBDD
     * @param idUsuario - Id usuario a eliminar
     * @param principal - Usuario logado
     * @param origen - Nombre desde dónde se realiza la petición
     * @return - Vista con la lista de usuarios actualizada
     */
    public ModelAndView eliminarUsuario(String idUsuario, Principal principal,String origen){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        if (origen.equals("lista")){
            vista.addObject("pagina","pages/usuarios/lista");

        }else{
            vista.addObject("pagina","pages/usuarios/buscar");
        }
        usuariosFeign.eliminarUsuario(idUsuario);
        vista.addObject("lista", usuariosFeign.listarUsuarios());
        return vista;
    }

    /**
     * Metodo para buscar un usuario en la BBDD
     * @param username_id - Id usuario a buscar
     * @param principal - Usuario logado
     * @return - Vista con el usuario buscado
     */
    public ModelAndView buscarUsuario(String username_id, Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina","pages/usuarios/buscar");
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("lista", usuariosFeign.buscarUsuario(username_id));
        return vista;
    }

    /**
     * Mñetodo para la modificacion de los datos de un usuario en la BBDD
     * @param usuarioModificadoDto - Usuario modificado
     * @param idPerfil - Id del perfil que tendrá el usuario modificado
     * @param file - Foto del usuario modificado
     * @param principal - Usuario logado
     * @param origen - Nombre desde dónde se realiza la petición
     * @return - Vista de la lista de usuarios actualizada
     */
    public ModelAndView modificarUsuario(UsuarioDTO usuarioModificadoDto,String idPerfil, MultipartFile file, Principal principal, String origen){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        if (origen.equals("lista")){
            vista.addObject("lista", usuariosFeign.listarUsuarios());
            vista.addObject("pagina","pages/usuarios/lista");
        }else{
            vista.addObject("lista", usuariosFeign.buscarUsuario(String.valueOf(usuarioModificadoDto.getIdUsuario())));
            vista.addObject("pagina","pages/usuarios/buscar");
        }
        try {
            byte[] fotoForm = file.getBytes();
            usuarioModificadoDto.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            usuarioModificadoDto.setPerfil(usuariosFeign.buscarPerfil(idPerfil));
            usuariosFeign.modificarUsuario(usuarioModificadoDto);
            if (usuarioModificadoDto.getUsername().equalsIgnoreCase(principal.getName())){
                vista.addObject("usuarioFoto", "data:image/png;base64,"+ usuariosFeign.buscarUsuario(usuarioModificadoDto.getUsername()).get(0).getFoto());
            }
        } catch (IOException e) {
            logger.error("Error al convertir en bytes la imagen del formulario: " + e.getMessage());
        } catch (Exception e) {
            logger.error("No se ha podido cargar la página de modificacion del usuario: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Método que carga una página con la lista de los usuarios
     * @param principal - Usuario Logado
     * @return - Vista con la lista de los usuarios
     */
    public ModelAndView paginaListaUsuarios(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("lista", usuariosFeign.listarUsuarios());
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("pagina","pages/usuarios/lista");
        return vista;
    }

    /**
     * Método que devuelve una lista de todos los perfiles/roles de la BBDD
     * @return - Lista de Dto tipo Perfil
     */
    public List<PerfilDTO> listaPerfiles(){
        return usuariosFeign.listaPerfiles();
    }

    /**
     * Método apra cambiar el password del usuario
     * @param campos - Contraseña actual, nueva y nueva repetida
     * @param principal - Usuario logado
     * @return - ResponseEntity del estado de la operación
     */
    public ResponseEntity cambiarPasswd(Map<String, String> campos, Principal principal) {
        List<UsuarioDTO> usuarios = usuariosFeign.buscarUsuario(principal.getName());
        return WebUtils.verificarPasswd(campos, usuarios.get(0), principal, usuariosFeign);
    }
}
