package com.saguadopro.gestionapartamentos.services.impl;

import com.saguadopro.gestionapartamentos.rest.dto.TipoModeloDTO;

import java.util.List;

public interface TipoModelosImp {


    Boolean crearTipoModelo(TipoModeloDTO tipoModeloDTO);

    Boolean eliminarTipoModelo(Integer idTipoModelo);

    Boolean modificarTipoModelo(TipoModeloDTO tipoModeloDTO);

    TipoModeloDTO buscarTipoModelo(Integer idTipoModelo);

    List<TipoModeloDTO> listaTiposModelos();

}
