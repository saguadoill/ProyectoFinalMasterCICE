package com.saguadopro.clietenweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartamentoDTO {

    private Long idApartamento;

    private TipoModeloDTO tipo;

    private Integer capacidad;

    private String direccion;

    private String piso;

    private String puerta;

    private String foto;

    private Boolean disponible;

    private PropietarioDTO propietario;
}
