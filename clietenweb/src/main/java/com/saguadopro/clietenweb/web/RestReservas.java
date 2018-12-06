package com.saguadopro.clietenweb.web;

import com.saguadopro.clietenweb.services.ReservasWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping(value = "/reservas")
public class RestReservas {

    @Autowired
    ReservasWebService reservasWebService;

    @RequestMapping(value = "/pendientes", method = RequestMethod.GET)
    public ModelAndView reservasPendientes(Principal principal){
        return reservasWebService.reservasPendientesService(principal);
    }

    @RequestMapping(value = "/finalizadas", method = RequestMethod.GET)
    public ModelAndView reservasFinalizadas(Principal principal){
        return reservasWebService.reservasFinalizadasService(principal);
    }

    @RequestMapping(value = "/activas", method = RequestMethod.GET)
    public ModelAndView reservasActivas(Principal principal){
        return reservasWebService.reservasActivasService(principal);
    }
}
