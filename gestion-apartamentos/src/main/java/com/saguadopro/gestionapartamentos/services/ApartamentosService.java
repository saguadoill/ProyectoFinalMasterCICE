package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.repositories.ApartamentosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.services.conversores.CapacidadConverter;
import com.saguadopro.gestionapartamentos.services.conversores.PropietarioConverter;
import com.saguadopro.gestionapartamentos.services.conversores.ApartamentoConverter;
import com.saguadopro.gestionapartamentos.services.conversores.HuespedConverter;
import com.saguadopro.gestionapartamentos.services.impl.ApartamentosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartamentosService implements ApartamentosImp {

    @Autowired
    private FotosService fotosService;

    @Autowired
    private ApartamentoConverter apartamentoConverter;

    @Autowired
    private ApartamentosRepo apartamentosRepo;

    @Override
    public Boolean crearApartamento(ApartamentoDTO apartamentoDTO) {
        if (apartamentoDTO != null) {
            System.out.println("entrando en donde la foto no esta vacia");
            Apartamento apartamentoBBDD = apartamentoConverter.dtoToApartamento(apartamentoDTO);
            apartamentosRepo.save(apartamentoBBDD);
            return true;
        } else {
//            logger.error("El usuario no se ha podido crear");
            return false;
        }

    }

    @Override
    public Boolean eliminarApartamento(Long idApartamento) {
        Apartamento apartamentoParaEliminar = apartamentosRepo.findById(idApartamento).get();
        try {
            apartamentosRepo.delete(apartamentoParaEliminar);
            return true;
        } catch (Exception e) {
//            logger.error("Usuario no se ha podido eliminar:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean modificarApartamento(ApartamentoDTO apartamentoDTOModificado) {
        try {
            Optional<Apartamento> apartamentoOriginal = apartamentosRepo.findById(apartamentoDTOModificado.getIdApartamento());
            if (apartamentoOriginal.isPresent()) {
                BufferedImage fotoUsuarioModificada = fotosService.decodificarFoto(apartamentoDTOModificado.getFoto());

                if (apartamentoOriginal.get().getCapacidad().getIdCapacidad() != apartamentoDTOModificado.getCapacidad().getIdCapacidad()) {
                    apartamentoOriginal.get().setCapacidad(CapacidadConverter.dtoToCapacidad(apartamentoDTOModificado.getCapacidad()));
                }
                if (!apartamentoOriginal.get().getDireccion().equals(apartamentoDTOModificado.getDireccion())) {
                    apartamentoOriginal.get().setDireccion(apartamentoDTOModificado.getDireccion());
                }
                if (apartamentoOriginal.get().getPropietario().getIdPropietario() != apartamentoDTOModificado.getPropietario().getIdPropietario()) {
                    apartamentoOriginal.get().setPropietario(PropietarioConverter.dtoToPropietario(apartamentoDTOModificado.getPropietario()));
                }
                if (!apartamentoOriginal.get().getPiso().equals(apartamentoDTOModificado.getPiso())) {
                    apartamentoOriginal.get().setPiso(apartamentoDTOModificado.getPiso());
                }
                if (apartamentoOriginal.get().getPuerta() != apartamentoDTOModificado.getPuerta()) {
                    apartamentoOriginal.get().setPuerta(apartamentoDTOModificado.getPuerta());
                }
//                if (apartamentoOriginal.get().getTipo() != apartamentoDTOModificado.getTipo()) {
//                    apartamentoOriginal.get().setTipo(apartamentoDTOModificado.getTipo());
//                }
                if (apartamentoOriginal.get().getHuesped().getIdHuesped() != apartamentoDTOModificado.getHuesped().getIdHuesped()) {
                    apartamentoOriginal.get().setHuesped(HuespedConverter.dtoToHuesped(apartamentoDTOModificado.getHuesped()));
                }
                if (!apartamentoDTOModificado.getFoto().equals("")) {
                    apartamentoOriginal.get().setFoto_url(fotosService.guardarFoto(apartamentoOriginal.get().getIdApartamento(), fotoUsuarioModificada));
                }
                apartamentosRepo.save(apartamentoOriginal.get());
                return true;
            } else {
                System.out.println("log4j: el usuario no se ha podido modificar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ApartamentoDTO> buscarApartamento(Long idApartamento) {
        List<ApartamentoDTO> apartamentosDtoEncontrado = new ArrayList<>();
        List<Apartamento> apartamentoEncontrado = new ArrayList<>();
        try {
            apartamentoEncontrado.addAll(apartamentosRepo.encontrarApartamentosPorId(idApartamento));
            for (Apartamento apartamento : apartamentoEncontrado) {
                apartamentosDtoEncontrado.add(apartamentoConverter.apartamentoToDto(apartamento));
            }
            return apartamentosDtoEncontrado;
        } catch (NumberFormatException e) {
            apartamentoEncontrado.addAll(apartamentosRepo.encontrarApartamentosPorId(idApartamento));
            for (Apartamento apartamento : apartamentoEncontrado) {
                apartamentosDtoEncontrado.add(apartamentoConverter.apartamentoToDto(apartamento));
            }
            return apartamentosDtoEncontrado;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<ApartamentoDTO> listaApartamentos() {
        List<ApartamentoDTO> listaApartamentosDto = new ArrayList<>();
        for (Apartamento apartamento : apartamentosRepo.findAll()) {
            listaApartamentosDto.add(apartamentoConverter.apartamentoToDto(apartamento));
        }
        return listaApartamentosDto;
    }
}
