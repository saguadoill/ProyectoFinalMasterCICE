package com.saguadopro.clietenweb.web;

import com.saguadopro.clietenweb.services.InicioWebService;
import com.saguadopro.clietenweb.services.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Clase Controladora de los endpoints encargados de gestionar la informacion inicial y el login de la aplicación.
 */
@RestController
public class RestInicio {

    @Autowired
    private InicioWebService inicioWebService;

    /**
     * Método encargado de cargar la pagina principal
     *
     * @param principal - Usuario logado
     * @return - Vista paágina principal
     * @see {@link InicioWebService}
     */
    @RequestMapping(value = {"/", "/index", "/home"}, method = RequestMethod.GET)
    public ModelAndView paginaInicio(Principal principal) {
        return inicioWebService.paginaInicioService(principal);
    }

    /**
     * Método encargado del login
     *
     * @return - Vista formulario login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView paginaLogin() {
        ModelAndView vista = new ModelAndView();
        vista.setViewName("login");
        return vista;
    }

    /**
     * Método encargado de mostrar la página de acceso denegado
     *
     * @param model     - Vista actual
     * @param principal - Usuario logado
     * @return - Vista página de acceso denegado
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Model model, Principal principal) {
        ModelAndView vista = new ModelAndView();
        vista.setViewName("403Page");
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hola " + principal.getName() //
                    + "<br> No tienes permiso para acceder a esta página!";
            model.addAttribute("message", message);

        }
        return vista;
    }
}
