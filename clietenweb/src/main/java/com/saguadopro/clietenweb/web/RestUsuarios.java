package com.saguadopro.clietenweb.web;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.services.InicioWebService;
import com.saguadopro.clietenweb.services.UsuariosWebServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Clase Controladora de los endpoints para la gestion de los apartamentos
 *
 * @see {@link UsuariosWebServices}
 */
@RestController
@RequestMapping(value = "/usuarios")
public class RestUsuarios {

    @Autowired
    private UsuariosWebServices usuariosWebServices;

    @Autowired
    private InicioWebService inicioWebService;

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView paginaUsuariosBuscar(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles", usuariosWebServices.listaPerfiles());
        vista.addObject("pagina", "pages/usuarios/buscar");
        return vista;
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscarUsuario(@RequestParam String username_id, Principal principal) {
        return usuariosWebServices.buscarUsuario(username_id, principal);
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public ModelAndView paginaUsuariosNuevo(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("listaPerfiles", usuariosWebServices.listaPerfiles());
        vista.addObject("pagina", "pages/usuarios/nuevo");
        return vista;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public ModelAndView crearNuevoUsuario(@ModelAttribute UsuarioDTO usuarioDTO, @RequestParam String idPerfil, @RequestParam MultipartFile file, Principal principal) {
        return usuariosWebServices.crearUsuario(usuarioDTO, idPerfil, file, principal);
    }

    @RequestMapping(value = "/lista", method = RequestMethod.GET)
    public ModelAndView paginaListaUsuarios(Principal principal) {
        return usuariosWebServices.paginaListaUsuarios(principal);
    }

    @RequestMapping(value = "/passwd", method = RequestMethod.GET)
    public ModelAndView paginaCambioPasswd(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina", "pages/usuarios/passwd");
        return vista;
    }

    @RequestMapping(value = "/campos", method = RequestMethod.GET)
    public ResponseEntity<List<String>> generarUserPass(@RequestParam String nombre, @RequestParam String apellidos) {
        return usuariosWebServices.generarUserPass(nombre, apellidos);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarUsuario(@RequestParam String idUsuario, @RequestParam String origen, Principal principal) {
        return usuariosWebServices.eliminarUsuario(idUsuario, principal, origen);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public ModelAndView modificarUsuario(@ModelAttribute UsuarioDTO usuarioModificadoDto, @RequestParam String idPerfil, @RequestParam MultipartFile file, Principal principal, @RequestParam String origen) {
        return usuariosWebServices.modificarUsuario(usuarioModificadoDto, idPerfil, file, principal, origen);
    }

    @RequestMapping(value = "/passwd", method = RequestMethod.POST)
    public ResponseEntity cambiarPasswd(@RequestBody Map<String, String> campos, Principal principal) {
        return usuariosWebServices.cambiarPasswd(campos, principal);
    }
}
