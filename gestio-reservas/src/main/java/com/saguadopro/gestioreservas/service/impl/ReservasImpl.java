package com.saguadopro.gestioreservas.service.impl;

import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;

import java.util.List;

/**
 * Interface para la gestion de reservas
 * @see {@link com.saguadopro.gestioreservas.service.ReservasService}
 */
public interface ReservasImpl {

    List<ReservaDTO> listaReservas();

    Boolean eliminarReserva(String idReserva);

    List<ReservaDTO> buscarReserva(String idReserva);

    void getBookinReservas(String xmlFile);

   Boolean modificarReserva(ReservaDTO reservaDTO);

}
