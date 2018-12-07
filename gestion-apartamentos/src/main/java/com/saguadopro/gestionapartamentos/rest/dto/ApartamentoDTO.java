package com.saguadopro.gestionapartamentos.rest.dto;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import com.saguadopro.gestionapartamentos.entities.Capacidad;
import lombok.Data;

@Data
public class ApartamentoDTO {

    private Long idApartamento;

    private Capacidad capacidad;

//    private Integer capacidad;

    private String direccion;

    private String piso;

    private String puerta;

    private String foto;

    private Boolean disponible;

    private Propietario propietario;
}
