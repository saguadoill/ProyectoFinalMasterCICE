package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.HuespedDTO;
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
            if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now())){
                reservasPendientes.add(reservaDTO);
                System.out.println("Reserva Pendiente: "+reservaDTO);
            }
        }
        List<ApartamentoDTO> apartDispoPorCapacidad  = apartamentosWebService.gestionarListaSegunOrigen("disponibles",null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/pendientes");
        return vista;
    }

    public ModelAndView reservasFinalizadasService(Principal principal){
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ReservaDTO> reservasPendientes = new ArrayList<>();
        for (ReservaDTO reservaDTO: reservasFeign.listaReservas()) {
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
            if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())){
                reservasPendientes.add(reservaDTO);
                System.out.println("Reserva Activas: "+reservaDTO);
            }
        }
        vista.addObject("lista", reservasPendientes);
        vista.addObject("pagina", "pages/reservas/activas");
        return vista;
    }

    public ModelAndView asignarApartamentoToReserva(Principal principal, String origen, String idApartamento, String idReserva, HuespedDTO huesped){
        ModelAndView vista = reservasPendientesService(principal);
        ReservaDTO reservaDT = reservasFeign.buscarReserva(idReserva);
        ApartamentoDTO apartamentoDTO = apartamentosWebService.buscarApartamentoPorId(idApartamento);
        apartamentoDTO.setHuesped(huesped);
        reservaDT.setIdApartamento(Long.parseLong(idApartamento));
        return vista;
    }
}
