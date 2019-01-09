package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.HuespedDTO;
import com.saguadopro.clietenweb.dto.ReservaDTO;
import com.saguadopro.clietenweb.feign.ReservasFeign;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de las páginas para la gestion de las reservas
 */
@Service
public class ReservasWebService {

    private static Logger logger = Logger.getLogger(ReservasWebService.class);

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    private ReservasFeign reservasFeign;

    @Autowired
    private ApartamentosWebService apartamentosWebService;

    /**
     * Método que muestra una lista de las reservas que aún están pendientes
     *
     * @param principal - Usuario logado
     * @return - Vista con las reservas pendientes
     */
    public ModelAndView reservasPendientes(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("pendientes", null));
        return vista;
    }

    /**
     * Método que muestra una lista de las reservas que están finalizadas
     *
     * @param principal - Usuario logado
     * @return - Vista con las reservas finalizadas
     */
    public ModelAndView reservasFinalizadas(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("finalizadas", null));
        return vista;
    }

    /**
     * Método que muestra una lista de las reservas que están activas
     *
     * @param principal - Usuario logado
     * @return - Vista con las reservas activas
     */
    public ModelAndView reservasActivas(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("activas", null));
        return vista;
    }

    /**
     * Metodo que asigna un apartamento a la reserva. Asignando un huesped a dicho apartamento
     *
     * @param principal     - Usuario logado
     * @param origen        - Nombre de la página desde donde se realiza la asignación
     * @param idApartamento - Id del apartamento por defecto a asignar
     * @param idReserva     - Id de la reserva a la que se va a asignar el apartamento
     * @param huesped       - Huesped responsable del apartamento.
     * @return - Vista de la página origen actualizada
     */
    public ModelAndView asignarApartamentoToReserva(Principal principal, String origen, String idApartamento, String idReserva, HuespedDTO huesped) {
        ReservaDTO reservaDTO = reservasFeign.buscarReserva(idReserva).get(0);
        ApartamentoDTO apartamentoDTO = apartamentosWebService.buscarApartamentoPorId(idApartamento);
        apartamentoDTO.setHuesped(huesped);
        reservaDTO.setIdApartamento(Long.parseLong(idApartamento));
        reservaDTO.setEstaAsignada(true);
        reservasFeign.modificarReserva(reservaDTO);
        return reservasPendientes(principal);
    }

    /**
     * Método que busca una reserva en la BBDD
     *
     * @param principal - Usuario logado
     * @param idReserva - Id reserva a buscar
     * @return - Vista que muestra la reserva buscada
     */
    public ModelAndView buscarReserva(Principal principal, String idReserva) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen("buscar", idReserva));
        if (!inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/usuarios/passwd");
        } else {
            vista.addObject("pagina", "pages/reservas/buscar");
        }
        return vista;
    }

    /**
     * Método que elimina una reserva de la BBDD
     *
     * @param idReserva - Id reserva a eliminar
     * @param principal - Usuario logado
     * @param origen    - Nombre página desde donde se realiza la petición
     * @return - Vista con la lista de  reservas actualizada
     */
    public ModelAndView eliminarReserva(String idReserva, Principal principal, String origen) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        reservasFeign.eliminarReserva(idReserva);
        List<ApartamentoDTO> apartDispoPorCapacidad = apartamentosWebService.gestionarListaSegunOrigen("disponibles", null);
        vista.addObject("listaPorCapacidad", apartDispoPorCapacidad);
        vista.addObject("lista", listaReservasSegunOrigen(origen, idReserva));
        return vista;
    }

    /**
     * Método que devuelve una lista de reservas dependiendo de la página que haya hehco la solicitud
     *
     * @param origen    - Nombre página desde donde se realiza la petición
     * @param idReserva - Id reserva a eliminar
     * @return - Lista de Dto tipo Reserva
     */
    private List<ReservaDTO> listaReservasSegunOrigen(String origen, String idReserva) {
        List<ReservaDTO> listaCompleta = reservasFeign.listaReservas();
        List<ReservaDTO> listaFiltrada = new ArrayList<>();
        try {
            if (origen.equals("activas")) {
                for (ReservaDTO reservaDTO : listaCompleta) {
                    if (reservaDTO.getFechaEntrada().isBefore(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
                        listaFiltrada.add(reservaDTO);
                    }
                }
                return listaFiltrada;
            } else if (origen.equals("finalizadas")) {
                for (ReservaDTO reservaDTO : listaCompleta) {
                    if (reservaDTO.getFechaSalida().isBefore(LocalDate.now())) {
                        listaFiltrada.add(reservaDTO);
                    }
                }
                return listaFiltrada;
            } else if (origen.equals("pendientes")) {
                for (ReservaDTO reservaDTO : listaCompleta) {
                    if (reservaDTO.getFechaEntrada().isAfter(LocalDate.now()) & reservaDTO.getFechaSalida().isAfter(LocalDate.now())) {
                        listaFiltrada.add(reservaDTO);
                    }
                }
            } else {
                listaFiltrada.addAll(reservasFeign.buscarReserva(idReserva));
            }
        } catch (Exception e) {
            logger.error("No se ha podido gestionar la lista de reservas segun el origen: " + e.getMessage());
        }
        return listaFiltrada;
    }
}
