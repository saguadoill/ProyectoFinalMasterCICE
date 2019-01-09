package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Servicio encargado de cargar los datos iniciales para el resto de servicios
 */
@Service
public class InicioWebService {

    private static Logger logger = Logger.getLogger(InicioWebService.class);

    @Autowired
    private UsuariosFeign usuariosFeign;

    /**
     * Método que carga los datos iniciales(menus, foto usuario , usuario logado y página principal a mostrar)
     *
     * @param principal - Usuario logado
     * @return - Vista de la página principal
     */
    public ModelAndView paginaInicioService(Principal principal) {
        UsuarioDTO usuarioDTO = usuariosFeign.buscarUsuario(principal.getName()).get(0);
        ModelAndView vista = new ModelAndView("home");
        try {
            vista.addObject("usuarioFoto", "data:image/png;base64," + usuarioDTO.getFoto());
            if (verificarCambioPasswd(principal)) {
                vista.addObject("pagina", "bienvenida");
            } else {
                vista.addObject("pagina", "pages/usuarios/passwd");
            }
        } catch (Exception e) {
            logger.error("No se han podido cargar los valores iniciales de la página ni del usuario: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Metodoq ue verifica si el usuario ha cambiado el password inicial
     *
     * @param principal - Usuario logado
     * @return - True; si el usuario ha cambiado el passwd. False; si no lo ha hecho aún
     */
    public Boolean verificarCambioPasswd(Principal principal) {
        UsuarioDTO usuarioDTO = usuariosFeign.buscarUsuario(principal.getName()).get(0);
        return usuarioDTO.getCambioPasswd();
    }
}
