package com.saguadopro.gestioreservas.conversor;

import com.saguadopro.gestioreservas.entities.Reserva;
import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import org.springframework.stereotype.Service;

@Service
public class Conversor {


    public static Reserva dtoToReserva(ReservaDTO reservaDTO){
        Reserva reserva = new Reserva();
        reserva.setIdReserva(reservaDTO.getIdReserva());
        reserva.setCliente(reservaDTO.getCliente());
        reserva.setFechaEntrada(reservaDTO.getFechaEntrada());
        reserva.setFechaSalida(reservaDTO.getFechaSalida());
        reserva.setIdApartamento(reservaDTO.getIdApartamento());
        reserva.setNumero_personas(reservaDTO.getCapacidad());
        reserva.setTieneParking(reservaDTO.getTieneParking());
        reserva.setPrecioTotal(reservaDTO.getPrecioTotal());
        reserva.setEstaAsignada(reservaDTO.getEstaAsignada());
        return reserva;
    }

    public static ReservaDTO reservaToDto(Reserva reserva){
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setIdReserva(reserva.getIdReserva());
        reservaDTO.setCliente(reserva.getCliente());
        reservaDTO.setFechaEntrada(reserva.getFechaEntrada());
        reservaDTO.setFechaSalida(reserva.getFechaSalida());
        reservaDTO.setIdApartamento(reserva.getIdApartamento());
        reservaDTO.setCapacidad(reserva.getNumero_personas());
        reservaDTO.setTieneParking(reserva.getTieneParking());
        reservaDTO.setEstaAsignada(reserva.getEstaAsignada());
        reservaDTO.setPrecioTotal(reserva.getPrecioTotal());
        return reservaDTO;
    }

}
