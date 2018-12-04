package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.TiposModelo;
import com.saguadopro.gestionapartamentos.repositories.TipoModelosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.TipoModeloDTO;
import com.saguadopro.gestionapartamentos.services.impl.TipoModelosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoModelosService implements TipoModelosImp {

    @Autowired
    TipoModelosRepo tipoModelosRepo;


    @Override
    public Boolean crearTipoModelo(TipoModeloDTO tipoModeloDTO) {
        return null;
    }

    @Override
    public Boolean eliminarTipoModelo(Integer idTipoModelo) {
        return null;
    }

    @Override
    public Boolean modificarTipoModelo(TipoModeloDTO propietarioDTO) {
        return null;
    }

    @Override
    public TipoModeloDTO buscarTipoModelo(Integer idTipoModelo) {
        TipoModeloDTO tipoModeloDTO = new TipoModeloDTO();
        TiposModelo tiposModelo  = tipoModelosRepo.findById(idTipoModelo).get();
        tipoModeloDTO.setIdTipoModelo(tiposModelo.getIdTipoModelo());
        tipoModeloDTO.setTipo(tiposModelo.getTipo());
        return tipoModeloDTO;
    }

    @Override
    public List<TipoModeloDTO> listaTiposModelos() {
        List<TipoModeloDTO> listaTipoModelosDTO = new ArrayList<>();
        for (TiposModelo tiposModelo : tipoModelosRepo.findAll()) {
            TipoModeloDTO tipoModeloDTO = new TipoModeloDTO();
            tipoModeloDTO.setIdTipoModelo(tiposModelo.getIdTipoModelo());
            tipoModeloDTO.setTipo(tiposModelo.getTipo());
            listaTipoModelosDTO.add(tipoModeloDTO);
        }
        return listaTipoModelosDTO;
    }

}
