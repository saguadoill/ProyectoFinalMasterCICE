package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Capacidad;
import com.saguadopro.gestionapartamentos.repositories.CapacidadRepo;
import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;
import com.saguadopro.gestionapartamentos.services.impl.CapacidadImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapacidadService implements CapacidadImp {

    @Autowired
    CapacidadRepo capacidadRepo;


    @Override
    public Boolean crearCapacidad(CapacidadDTO capacidadDTO) {
        return null;
    }

    @Override
    public Boolean eliminarCapacidad(Integer idTipoModelo) {
        return null;
    }

    @Override
    public Boolean modificarCapacidad(CapacidadDTO propietarioDTO) {
        return null;
    }

    @Override
    public CapacidadDTO buscarPorCapacidad(Integer idCapacidad) {
        CapacidadDTO capacidadDTO = new CapacidadDTO();
        Capacidad capacidad = capacidadRepo.findById(idCapacidad).get();
        capacidadDTO.setIdCapacidad(capacidad.getIdCapacidad());
        capacidadDTO.setMaxPersonas(capacidad.getMaxPersonas());
        return capacidadDTO;
    }

    @Override
    public List<CapacidadDTO> listaCapacidades() {
        List<CapacidadDTO> listaTipoModelosDTO = new ArrayList<>();
        for (Capacidad capacidad : capacidadRepo.findAll()) {
            CapacidadDTO capacidadDTO = new CapacidadDTO();
            capacidadDTO.setIdCapacidad(capacidad.getIdCapacidad());
            capacidadDTO.setMaxPersonas(capacidad.getMaxPersonas());
            listaTipoModelosDTO.add(capacidadDTO);
        }
        return listaTipoModelosDTO;
    }

}
