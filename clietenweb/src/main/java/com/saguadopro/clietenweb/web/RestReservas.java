package com.saguadopro.clietenweb.web;

import com.saguadopro.clietenweb.dto.HuespedDTO;
import com.saguadopro.clietenweb.services.ReservasWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/asignar", method = RequestMethod.POST)
    public ModelAndView asignarApartamento(Principal principal, @RequestParam String origen, @RequestParam String apartamentoSeleccionado,
                                           @RequestParam String idReserva, @ModelAttribute HuespedDTO huesped){
        return reservasWebService.asignarApartamentoToReserva(principal,origen,apartamentoSeleccionado,idReserva,huesped);
    }
}
