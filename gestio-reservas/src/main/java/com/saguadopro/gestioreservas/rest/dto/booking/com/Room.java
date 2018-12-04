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
public class Room {


    private String arrival_date;

    private String currencycode;

    private String departure_date;

    private Long id;

    private String meal_plan;

    private Integer numberofguests;

    private Long roomreservation_id;

    private Double totalprice;

}
