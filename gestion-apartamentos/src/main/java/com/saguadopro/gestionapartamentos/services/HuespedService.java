package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import com.saguadopro.gestionapartamentos.services.impl.HuespedImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuespedService implements HuespedImp {

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
    public HuespedDTO buscarPropietario(Long idHuesped) {
        return null;
    }

    @Override
    public List<HuespedDTO> listaHuespedes() {
        return null;
    }
}
