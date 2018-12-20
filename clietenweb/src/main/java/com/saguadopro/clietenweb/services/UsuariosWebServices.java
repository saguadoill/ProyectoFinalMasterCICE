package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.PerfilDTO;
import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
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

@Service
public class UsuariosWebServices {

    @Autowired
    UsuariosFeign usuariosFeign;

    @Autowired
    InicioWebService inicioWebService;


    public ModelAndView crearUsuario(UsuarioDTO usuarioDTO, String idPerfil,MultipartFile file, Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("pagina","pages/usuarios/nuevo");

//        UsuarioDTO usuarioDTO = new UsuarioDTO(null,username,passwd,usuariosFeign.buscarPerfil(idPerfil),nombre,apellidos,null, null);

        try{
            usuarioDTO.setPerfil(usuariosFeign.buscarPerfil(idPerfil));
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuarioDTO.setPasswd(passwordEncoder.encode(usuarioDTO.getPasswd()));
            byte[] fotoForm = file.getBytes();
            usuarioDTO.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            usuarioDTO.setCambioPasswd(false);
            if (usuariosFeign.crearUsuarioFeign(usuarioDTO) == HttpStatus.CREATED) {
                System.out.println("Usuarrio creado");
                return vista;
            } else {
                //log4j
                System.out.println("Usuarrio no creado");
                return vista;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vista;

    }

    public ResponseEntity<List<String>> generarUserPass(String nombre, String apellidos){
        System.out.println("Llega al service: "+nombre+" "+apellidos);
        try {
            List<String> lista = usuariosFeign.generarUserPass(nombre, apellidos);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (NullPointerException e) {
            //log4j indicar que no hay respuesta del servidor para generar username y contraseña
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
//
//    public ResponseEntity eliminarUsuarioService(String idUsuario){
//        if (gestionUsuariosFeign.eliminarUsuario(idUsuario)) {
//            return new ResponseEntity(HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
//        }
//    }


    public ModelAndView eliminarUsuario(String idUsuario, Principal principal,String origen){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        if (origen.equals("lista")){
            vista.addObject("pagina","pages/usuarios/lista");

        }else{
            vista.addObject("pagina","pages/usuarios/buscar");
        }
        if (usuariosFeign.eliminarUsuario(idUsuario)) {
            vista.addObject("mensaje", "Usuario eliminado");
        } else {
            vista.addObject("mensaje", "No ha sido posible eliminar al usuario");
        }
        vista.addObject("lista", usuariosFeign.listarUsuarios());
        return vista;
    }

    public ModelAndView buscarUsuario(String username_id, Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina","pages/usuarios/buscar");
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("lista", usuariosFeign.buscarUsuario(username_id));
        return vista;
    }

    public ModelAndView modificarUsuario(UsuarioDTO usuarioModificadoDto,String idPerfil, MultipartFile file, Principal principal, String origen){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        try {
            byte[] fotoForm = file.getBytes();
            usuarioModificadoDto.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            usuarioModificadoDto.setPerfil(usuariosFeign.buscarPerfil(idPerfil));
            usuariosFeign.modificarUsuario(usuarioModificadoDto);
            if (usuarioModificadoDto.getUsername().equals(principal.getName())){
                vista.addObject("usuarioFoto", "data:image/png;base64,"+ usuariosFeign.buscarUsuario(usuarioModificadoDto.getUsername()).get(0).getFoto());
            }
//            vista.addObject("usuarioFoto", "data:image/png;base64,"+ usuariosFeign.buscarUsuario(usuarioModificadoDto.getUsername()).get(0).getFoto());
            if (origen.equals("lista")){
                vista.addObject("lista", usuariosFeign.listarUsuarios());
                vista.addObject("pagina","pages/usuarios/lista");
            }else{
                vista.addObject("lista", usuariosFeign.buscarUsuario(String.valueOf(usuarioModificadoDto.getIdUsuario())));
                vista.addObject("pagina","pages/usuarios/buscar");
            }
            return vista;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vista;
    }

    public ModelAndView paginaListaUsuarios(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("lista", usuariosFeign.listarUsuarios());
        vista.addObject("listaPerfiles",usuariosFeign.listaPerfiles());
        vista.addObject("pagina","pages/usuarios/lista");
        return vista;
    }

    public List<PerfilDTO> listaPerfiles(){
        return usuariosFeign.listaPerfiles();
    }

    public ResponseEntity cambiarPasswd(Map<String, String> campos, Principal principal) {
        List<UsuarioDTO> usuarios = usuariosFeign.buscarUsuario(principal.getName());
        return WebUtils.verificarPasswd(campos, usuarios.get(0), principal, usuariosFeign);
    }
}
