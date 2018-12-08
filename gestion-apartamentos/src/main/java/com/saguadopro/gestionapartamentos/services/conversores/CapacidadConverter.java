package com.saguadopro.gestionapartamentos.services.conversores;

import com.saguadopro.gestionapartamentos.entities.Capacidad;
import com.saguadopro.gestionapartamentos.entities.Huesped;
import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import org.springframework.stereotype.Service;

@Service
public class CapacidadConverter {

    public static CapacidadDTO capacidadToDto(Capacidad capacidad) {
        CapacidadDTO capacidadDTO = new CapacidadDTO();
        capacidadDTO.setIdCapacidad(capacidad.getIdCapacidad());
        capacidadDTO.setMaxPersonas(capacidad.getMaxPersonas());
        return capacidadDTO;
    }

    public static Capacidad dtoToCapacidad(CapacidadDTO capacidadDTO) {
        Capacidad capacidad = new Capacidad();
        capacidad.setIdCapacidad(capacidadDTO.getIdCapacidad());
        capacidad.setMaxPersonas(capacidadDTO.getMaxPersonas());
        return capacidad;
    }

}
