package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ReservaDTO;
import com.saguadopro.clietenweb.feign.ReservasFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservasWebService {

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    ReservasFeign reservasFeign;

    @Autowired
    ApartamentosWebService apartamentosWebService;

    public ModelAndView reservasPendientesService(Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasPendientes = new ArrayList<>();
        for (ReservaDTO reservaDTO: reservasFeign.listaReservas()) {
            System.out.println("Reserva: "+reservaDTO);
            if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now())){
                reservasPendientes.add(reservaDTO);
                System.out.println("Reserva Pendiente: "+reservaDTO);
            }
        }
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/pendientes");
        return vista;
    }

    public ModelAndView reservasFinalizadasService(Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasPendientes = new ArrayList<>();
        for (ReservaDTO reservaDTO: reservasFeign.listaReservas()) {
            System.out.println("Reserva: "+reservaDTO);
            if (reservaDTO.getFechaSalida().isBefore(LocalDate.now())){
                reservasPendientes.add(reservaDTO);
                System.out.println("Reserva Finalizadas: "+reservaDTO);
            }
        }
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/finalizadas");
        return vista;
    }

    public ModelAndView reservasActivasService(Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasPendientes = new ArrayList<>();
        for (ReservaDTO reservaDTO: reservasFeign.listaReservas()) {
            System.out.println("Reserva: "+reservaDTO);
            if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())){
                reservasPendientes.add(reservaDTO);
                System.out.println("Reserva Activas: "+reservaDTO);
            }
        }
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/activas");
        return vista;
    }

    //TODO:  before te pone las actuale y las activas. Hay q poner en las activas que sean before y after fecha actual
}
