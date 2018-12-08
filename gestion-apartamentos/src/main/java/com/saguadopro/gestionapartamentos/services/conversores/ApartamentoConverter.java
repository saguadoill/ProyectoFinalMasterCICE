package com.saguadopro.gestionapartamentos.services.conversores;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.services.FotosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartamentoConverter {

    @Autowired
    FotosService fotosService;


    public ApartamentoDTO apartamentoToDto(Apartamento apartamento) {
        ApartamentoDTO apartamentoDTO = new ApartamentoDTO();
        apartamentoDTO.setIdApartamento(apartamento.getIdApartamento());
        apartamentoDTO.setCapacidad(CapacidadConverter.capacidadToDto(apartamento.getCapacidad()));
        apartamentoDTO.setDireccion(apartamento.getDireccion());
        apartamentoDTO.setPropietario(PropietarioConverter.propietarioToDto(apartamento.getPropietario()));
        apartamentoDTO.setPiso(apartamento.getPiso());
        apartamentoDTO.setPuerta(apartamento.getPuerta());
//        apartamentoDTO.setTipo(apartamento.getTipo());
        apartamentoDTO.setHuesped(HuespedConverter.huespedToDto(apartamento.getHuesped()));
        apartamentoDTO.setFoto(fotosService.codificarFoto(apartamento.getFoto_url()));
        return apartamentoDTO;
    }

    public Apartamento dtoToApartamento(ApartamentoDTO apartamentoDTO) {
        Apartamento apartamento = new Apartamento();
//        apartamento.setTipo(apartamentoDTO.getTipo());
        apartamento.setPuerta(apartamentoDTO.getPuerta());
        apartamento.setPiso(apartamentoDTO.getPiso());
        apartamento.setPropietario(PropietarioConverter.dtoToPropietario(apartamentoDTO.getPropietario()));
        apartamento.setDireccion(apartamentoDTO.getDireccion());
        apartamento.setCapacidad(CapacidadConverter.dtoToCapacidad(apartamentoDTO.getCapacidad()));
        apartamento.setIdApartamento(apartamentoDTO.getIdApartamento());
        apartamento.setHuesped(HuespedConverter.dtoToHuesped(apartamentoDTO.getHuesped()));
        if (!apartamentoDTO.getFoto().isEmpty() || !apartamentoDTO.getFoto().equals("")){
            apartamento.setFoto_url(fotosService.guardarFoto(apartamentoDTO.getIdApartamento(),
                    fotosService.decodificarFoto(apartamentoDTO.getFoto())));
        }else {
            apartamento.setFoto_url("src/main/resources/fotos/logo_apartamento.png");
        }
        return apartamento;
    }
}
