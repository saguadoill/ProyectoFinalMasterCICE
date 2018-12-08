package com.saguadopro.gestionapartamentos.services.conversores;

import com.saguadopro.gestionapartamentos.entities.Huesped;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import org.springframework.stereotype.Service;

@Service
public class HuespedConverter {

    public static HuespedDTO huespedToDto(Huesped huesped) {
        HuespedDTO huespedDTO = new HuespedDTO();
        huespedDTO.setIdHuesped(huesped.getIdHuesped());
        huespedDTO.setNombre(huesped.getNombre());
        huespedDTO.setApellidos(huesped.getApellidos());
        huespedDTO.setTelefono(huesped.getTelefono());
        huespedDTO.setEmail(huesped.getEmail());
        huespedDTO.setDni(huesped.getDni());
        return huespedDTO;
    }

    public static Huesped dtoToHuesped(HuespedDTO huespedDTO) {
        Huesped huesped = new Huesped();
        huesped.setIdHuesped(huespedDTO.getIdHuesped());
        huesped.setNombre(huespedDTO.getNombre());
        huesped.setApellidos(huespedDTO.getApellidos());
        huesped.setTelefono(huespedDTO.getTelefono());
        huesped.setEmail(huespedDTO.getEmail());
        huesped.setDni(huespedDTO.getDni());
        return huesped;
    }

}
