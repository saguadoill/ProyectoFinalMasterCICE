package com.saguadopro.gestioreservas.service.impl;

import com.saguadopro.gestioreservas.entities.Reserva;
import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;

import java.util.List;

public interface ReservasImpl {

    List<ReservaDTO> listaReservas();

    Boolean eliminarReserva(ReservaDTO reservaDTO);

    ReservaDTO buscarReserva(Long idReserva);

}
