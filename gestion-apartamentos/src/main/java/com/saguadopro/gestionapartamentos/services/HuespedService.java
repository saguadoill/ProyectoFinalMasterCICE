package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.repositories.HuespedRepo;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import com.saguadopro.gestionapartamentos.services.impl.HuespedImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuespedService implements HuespedImp {

    @Autowired
    HuespedRepo huespedRepo;

    @Autowired
    ConversorFeign conversorFeign;

    @Override
    public Boolean addHuesped(HuespedDTO huespedDTO) {
        return null;
    }

    @Override
    public Boolean eliminarHuesped(Long idHuesped) {
        return null;
    }

    @Override
    public Boolean modificarHuesped(HuespedDTO huespedDTO) {
        return null;
    }

    @Override
    public HuespedDTO buscarHuesped(Long idHuesped) {
        return conversorFeign.huespedEntityToDto(huespedRepo.findById(idHuesped).get());
    }

    @Override
    public List<HuespedDTO> listaHuespedes() {
        return null;
    }
}
