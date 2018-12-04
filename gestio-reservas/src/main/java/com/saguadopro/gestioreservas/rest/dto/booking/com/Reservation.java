package com.saguadopro.gestioreservas.rest.dto.booking.com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {


    private Customer customer;

    private String date;

    private Long id;

    private Room room;

    private String time;

}
