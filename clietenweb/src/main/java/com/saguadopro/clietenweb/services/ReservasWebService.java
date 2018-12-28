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
//        List<ReservaDTO> reservasPendientes = new ArrayList<>();
//        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
//            if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
////                System.out.println("reserva"+reservaDTO.getIdReserva()+" esta asignada? "+reservaDTO.getEstaAsignada());
//                reservasPendientes.add(reservaDTO);
//            }
//        }
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("pendientes",null));
        vista.addObject("pagina", "pages/reservas/pendientes");
        return vista;
    }

    public ModelAndView reservasFinalizadasService(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
//        List<ReservaDTO> reservasFinalizadas = new ArrayList<>();
//        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
//            if (reservaDTO.getFechaSalida().isBefore(LocalDate.now())) {
//                reservasFinalizadas.add(reservaDTO);
//            }
//        }
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("finalizadas",null));
        vista.addObject("pagina", "pages/reservas/finalizadas");
        return vista;
    }

    public ModelAndView reservasActivasService(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
//        List<ReservaDTO> reservasActivas = new ArrayList<>();
//        for (ReservaDTO reservaDTO : reservasFeign.listaReservas()) {
//            if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
//                reservasActivas.add(reservaDTO);
//            }
//        }
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("activas",null));
        vista.addObject("pagina", "pages/reservas/activas");
        return vista;
    }

    public ModelAndView asignarApartamentoToReserva(Principal principal, String origen, String idApartamento, String idReserva, HuespedDTO huesped) {
        ReservaDTO reservaDTO = reservasFeign.buscarReserva(idReserva).get(0);
        ApartamentoDTO apartamentoDTO = apartamentosWebService.buscarApartamentoPorId(idApartamento);
        apartamentoDTO.setHuesped(huesped);
        reservaDTO.setIdApartamento(Long.parseLong(idApartamento));
        reservaDTO.setEstaAsignada(true);
        reservasFeign.modificarReserva(reservaDTO);
        return reservasPendientesService(principal);
    }

    public ModelAndView buscarReserva(Principal principal, String idReserva) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("pagina", "pages/reservas/buscar");
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista",listaReservasSegunOrigen("buscar",idReserva));
        return vista;
    }

    public ModelAndView eliminarReserva(String idReserva, Principal principal, String origen) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        reservasFeign.eliminarReserva(idReserva);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista",listaReservasSegunOrigen(origen, idReserva));
        vista.addObject("pagina", "pages/reservas/"+origen);
        return vista;
    }

    public List<ReservaDTO> listaReservasSegunOrigen(String origen, String idReserva){
        List<ReservaDTO> listaCompleta = reservasFeign.listaReservas();
        List<ReservaDTO> listaFiltrada = new ArrayList<>();
        if (origen.equals("activas")){
            for (ReservaDTO reservaDTO : listaCompleta) {
                if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
                    listaFiltrada.add(reservaDTO);
                }
            }
            return listaFiltrada;
        }else if (origen.equals("finalizadas")){
           for (ReservaDTO reservaDTO : listaCompleta) {
               if (reservaDTO.getFechaSalida().isBefore(LocalDate.now())) {
                   listaFiltrada.add(reservaDTO);
               }
            }
            return listaFiltrada;
        }else if (origen.equals("pendientes")){
            for (ReservaDTO reservaDTO : listaCompleta) {
                if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
                    listaFiltrada.add(reservaDTO);
                }
            }
            return listaFiltrada;
        }else{
            try {
                listaFiltrada.addAll(reservasFeign.buscarReserva(idReserva));
                return listaFiltrada;
            }catch (Exception e){
               return null;
            }
        }
    }
}
