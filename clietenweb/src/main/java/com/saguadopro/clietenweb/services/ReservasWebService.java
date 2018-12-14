package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.HuespedDTO;
import com.saguadopro.clietenweb.dto.ReservaDTO;
import com.saguadopro.clietenweb.feign.ReservasFeign;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    public ModelAndView reservasPendientesService(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasPendientes = new ArrayList<>();
        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
            if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
//                System.out.println("reserva"+reservaDTO.getIdReserva()+" esta asignada? "+reservaDTO.getEstaAsignada());
                reservasPendientes.add(reservaDTO);
            }
        }
        //TODO: poner tittle an la pagina pendientes a los td de las fechas para ver que sale
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/pendientes");
        return vista;
    }

    public ModelAndView reservasFinalizadasService(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasFinalizadas = new ArrayList<>();
        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
            if (reservaDTO.getFechaSalida().isBefore(LocalDate.now())) {
                reservasFinalizadas.add(reservaDTO);
            }
        }
        vista.addObject("lista", reservasFinalizadas);
        vista.addObject("pagina", "pages/reservas/finalizadas");
        return vista;
    }

    public ModelAndView reservasActivasService(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasActivas = new ArrayList<>();
        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
            if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
                reservasActivas.add(reservaDTO);
            }
        }
        vista.addObject("lista", reservasActivas);
        vista.addObject("pagina", "pages/reservas/activas");
        return vista;
    }

    public ModelAndView asignarApartamentoToReserva(Principal principal, String origen, String idApartamento, String idReserva, HuespedDTO huesped) {
        ReservaDTO reservaDTO = reservasFeign.buscarReserva(idReserva);
        ApartamentoDTO apartamentoDTO = apartamentosWebService.buscarApartamentoPorId(idApartamento);
        apartamentoDTO.setHuesped(huesped);
        reservaDTO.setIdApartamento(Long.parseLong(idApartamento));
        reservaDTO.setEstaAsignada(true);
        reservasFeign.modificarReserva(reservaDTO);
        return reservasPendientesService(principal);
    }
}
