package com.saguadopro.gestioreservas.rest.dto.booking.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@XmlRootElement(name = "reservations")
public class Reservations {

    private List<Reservation> reservations;

    @XmlElement(name = "reservation")
    public List<Reservation> getReservations() {
        return reservations;
    }
}
