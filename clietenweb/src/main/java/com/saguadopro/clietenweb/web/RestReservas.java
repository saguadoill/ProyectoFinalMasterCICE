package com.saguadopro.clietenweb.web;

import com.saguadopro.clietenweb.dto.HuespedDTO;
import com.saguadopro.clietenweb.services.InicioWebService;
import com.saguadopro.clietenweb.services.ReservasWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Clase Controladora de los endpoints para la gestion de los apartamentos
 *
 * @see {@link ReservasWebService}
 */
@RestController
@RequestMapping(value = "/reservas")
public class RestReservas {

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    private ReservasWebService reservasWebService;

    @RequestMapping(value = "/pendientes", method = RequestMethod.GET)
    public ModelAndView reservasPendientes(Principal principal) {
        ModelAndView vista = reservasWebService.reservasPendientes(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/reservas/pendientes");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/finalizadas", method = RequestMethod.GET)
    public ModelAndView reservasFinalizadas(Principal principal) {
        ModelAndView vista = reservasWebService.reservasFinalizadas(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/reservas/finalizadas");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/activas", method = RequestMethod.GET)
    public ModelAndView reservasActivas(Principal principal) {
        ModelAndView vista = reservasWebService.reservasActivas(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/reservas/activas");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/asignar", method = RequestMethod.POST)
    public ModelAndView asignarApartamento(Principal principal, @RequestParam String origen, @RequestParam String apartamentoSeleccionado,
                                           @RequestParam String idReserva, @ModelAttribute HuespedDTO huesped) {
        return reservasWebService.asignarApartamentoToReserva(principal, origen, apartamentoSeleccionado, idReserva, huesped);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView paginaBuscarReserva(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/reservas/buscar");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscarreserva(@RequestParam String idReserva, Principal principal) {
        return reservasWebService.buscarReserva(principal, idReserva);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarReserva(@RequestParam String idReserva, @RequestParam String origen, Principal principal) {
        ModelAndView vista = reservasWebService.eliminarReserva(idReserva, principal, origen);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/reservas/" + origen);
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }
}
