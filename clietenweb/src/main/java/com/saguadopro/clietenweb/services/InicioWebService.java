package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.UsuarioDTO;
import com.saguadopro.clietenweb.feign.UsuariosFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Service
public class InicioWebService {

    @Autowired
    UsuariosFeign usuariosFeign;

    public ModelAndView paginaInicioService(Principal principal){
        UsuarioDTO usuarioDTO = usuariosFeign.buscarUsuario(principal.getName()).get(0);
        ModelAndView vista = new ModelAndView("home");
        vista.addObject("usuarioFoto", "data:image/png;base64,"+usuarioDTO.getFoto());
        if (!usuarioDTO.getCambioPasswd()){
            vista.addObject("pagina","pages/usuarios/passwd");
        }else {
            vista.addObject("pagina","bienvenida");
        }
        return vista;
    }
}
