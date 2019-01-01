package com.saguadopro.gestioreservas.rest.dto.booking.com;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * POJO  de la lista de reservas que envia Booking
 */
@XmlRootElement(name = "reservations")
public class Reservations {

    /**
     * Lista de todas las reserva que se han realizado desde Booking
     */
    private List<Reservation> listaReservas;

    /**
     * Metodo para poder obtener la lista de las reservas.
     * @return
     */
    @XmlElement(name = "reservation")
    public List<Reservation> getListaReservas() {
        return listaReservas;
    }
//
//    /**
//     * Metodo para guardar la lista de reservas
//     * @param listaReservas
//     */
//    public void setListaReservas(List<Reservation> listaReservas) {
//        this.listaReservas = listaReservas;
//    }
}
