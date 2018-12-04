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

@RestController
public class RestInicio {

    @Autowired
    InicioWebService inicioWebService;

    @RequestMapping(value = {"/", "/index", "/home"}, method = RequestMethod.GET)
    public ModelAndView paginaInicio(Principal principal) {
        return inicioWebService.paginaInicioService(principal);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView paginaLogin() {
        ModelAndView vista = new ModelAndView();
        vista.setViewName("login");
        return vista;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

}
