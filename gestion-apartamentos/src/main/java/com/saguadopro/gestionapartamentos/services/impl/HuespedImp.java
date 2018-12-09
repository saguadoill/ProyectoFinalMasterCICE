package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;

import java.util.List;

public interface HuespedImp {

    Boolean addHuesped(HuespedDTO huespedDTO);

    Boolean eliminarHuesped(Long idHuesped);

    Boolean modificarHuesped(HuespedDTO huespedDTO);

    HuespedDTO buscarHuesped(Long idHuesped);

    List<HuespedDTO> listaHuespedes();

}
