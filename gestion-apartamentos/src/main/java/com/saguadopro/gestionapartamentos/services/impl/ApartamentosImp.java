package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;

import java.awt.*;
import java.util.List;

public interface ApartamentosImp {

    Boolean crearApartamento(ApartamentoDTO apartamentoDTO);

    Boolean eliminarApartamento(Long idApartamento);

    Boolean modificarApartamento(ApartamentoDTO apartamentoDTO);

    List<ApartamentoDTO> buscarApartamento(Long idApartamento);

    List<ApartamentoDTO> listaApartamentos();

}
