package com.saguadopro.clietenweb.dto;

import lombok.Data;

@Data
public class ApartamentoDTO {

    private Long idApartamento;

    private CapacidadDTO capacidad;

//    private Integer capacidad;

    private String direccion;

    private String piso;

    private String puerta;

    private String foto;

    private HuespedDTO huesped;

    private PropietarioDTO propietario;
}
