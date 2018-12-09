package com.saguadopro.gestionapartamentos.services.conversores;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import org.springframework.stereotype.Service;

@Service
public class ApartamentoConverter {


    public static ApartamentoDTO apartamentoToDto(Apartamento apartamento) {

        ApartamentoDTO apartamentoDTO = new ApartamentoDTO();
        apartamentoDTO.setIdApartamento(apartamento.getIdApartamento());
        apartamentoDTO.setCapacidad(CapacidadConverter.capacidadToDto(apartamento.getCapacidad()));
        apartamentoDTO.setDireccion(apartamento.getDireccion());
        apartamentoDTO.setPropietario(PropietarioConverter.propietarioToDto(apartamento.getPropietario()));
        apartamentoDTO.setPiso(apartamento.getPiso());
        apartamentoDTO.setPuerta(apartamento.getPuerta());
//        apartamentoDTO.setTipo(apartamento.getTipo());
        if (apartamento.getHuesped() != null) {
            apartamentoDTO.setHuesped(HuespedConverter.huespedToDto(apartamento.getHuesped()));
        }
        apartamentoDTO.setFoto(FotoConverter.codificarFoto(apartamento.getFoto_url()));
        return apartamentoDTO;
    }

    public static Apartamento dtoToApartamento(ApartamentoDTO apartamentoDTO) {
        Apartamento apartamento = new Apartamento();
//        apartamento.setTipo(apartamentoDTO.getTipo());
        apartamento.setPuerta(apartamentoDTO.getPuerta());
        apartamento.setPiso(apartamentoDTO.getPiso());
        apartamento.setPropietario(PropietarioConverter.dtoToPropietario(apartamentoDTO.getPropietario()));
        apartamento.setDireccion(apartamentoDTO.getDireccion());
        apartamento.setCapacidad(CapacidadConverter.dtoToCapacidad(apartamentoDTO.getCapacidad()));
        apartamento.setIdApartamento(apartamentoDTO.getIdApartamento());
        if (apartamentoDTO.getHuesped() != null)apartamento.setHuesped(HuespedConverter.dtoToHuesped(apartamentoDTO.getHuesped()));
        if (!apartamentoDTO.getFoto().isEmpty() || !apartamentoDTO.getFoto().equals("")){
            apartamento.setFoto_url(FotoConverter.guardarFoto(apartamentoDTO.getIdApartamento(),
                    FotoConverter.decodificarFoto(apartamentoDTO.getFoto())));
        }else {
            apartamento.setFoto_url("src/main/resources/fotos/logo_apartamento.png");
        }
        return apartamento;
    }
}
