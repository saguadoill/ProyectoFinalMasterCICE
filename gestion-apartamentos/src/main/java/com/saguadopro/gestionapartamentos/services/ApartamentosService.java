package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.feign.FotosFeign;
import com.saguadopro.gestionapartamentos.repositories.ApartamentosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.services.impl.ApartamentosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApartamentosService implements ApartamentosImp {

    @Autowired
    private ApartamentosRepo apartamentosRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    @Autowired
    private FotosFeign fotosFeign;

    @Override
    public Boolean crearApartamento(ApartamentoDTO apartamentoDTO) {
        if (apartamentoDTO != null) {
            System.out.println("entrando en donde la foto no esta vacia");
            Apartamento apartamentoBBDD = conversorFeign.apartamentoDtoToEntity(apartamentoDTO);
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
        System.out.println("Apartamneto Modificado: "+apartamentoDTOModificado.toString());
        try {
            Optional<Apartamento> apartamentoOriginal = apartamentosRepo.findById(apartamentoDTOModificado.getIdApartamento());
            System.out.println("Apartamneto Original: "+apartamentoOriginal.toString());
            if (apartamentoOriginal.isPresent()) {
                if (apartamentoOriginal.get().getCapacidad().getIdCapacidad() != apartamentoDTOModificado.getCapacidad().getIdCapacidad()) {
                    apartamentoOriginal.get().setCapacidad(conversorFeign.capacidadDtoToEntity(apartamentoDTOModificado.getCapacidad()));
                }
                if (!apartamentoOriginal.get().getDireccion().equals(apartamentoDTOModificado.getDireccion())) {
                    apartamentoOriginal.get().setDireccion(apartamentoDTOModificado.getDireccion());
                }
                if (apartamentoOriginal.get().getPropietario().getIdPropietario() != apartamentoDTOModificado.getPropietario().getIdPropietario()) {
                    apartamentoOriginal.get().setPropietario(conversorFeign.propietarioDtoToEntity(apartamentoDTOModificado.getPropietario()));
                }
                if (!apartamentoOriginal.get().getPiso().equals(apartamentoDTOModificado.getPiso())) {
                    apartamentoOriginal.get().setPiso(apartamentoDTOModificado.getPiso());
                }
                if (apartamentoOriginal.get().getPuerta() != apartamentoDTOModificado.getPuerta()) {
                    apartamentoOriginal.get().setPuerta(apartamentoDTOModificado.getPuerta());
                }
                if (apartamentoDTOModificado.getHuesped() != null){
                    apartamentoOriginal.get().setHuesped(conversorFeign.huespedDtoToEntity(apartamentoDTOModificado.getHuesped()));
                } else{
                    apartamentoOriginal.get().setHuesped(null);
                }
                if (!apartamentoDTOModificado.getFoto().equals("")) {
                    apartamentoOriginal.get().setFoto_url(fotosFeign.guardarFoto(String.valueOf(apartamentoOriginal.get().getIdApartamento()), apartamentoDTOModificado.getFoto().getBytes(),"apartamento"));
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
       try {
           List<ApartamentoDTO> apartamentosDtoEncontrado = new ArrayList<>();
           List<Apartamento> apartamentoEncontrado = new ArrayList<>(apartamentosRepo.encontrarApartamentosPorId(idApartamento));
           for (Apartamento apartamento : apartamentoEncontrado) {
               apartamentosDtoEncontrado.add(conversorFeign.apartamentoEntityToDto(apartamento));
           }
           return apartamentosDtoEncontrado;

       }catch (Exception e){
           return null;
       }
    }

    @Override
    public List<ApartamentoDTO> listaApartamentos() {
        List<ApartamentoDTO> listaApartamentosDto = new ArrayList<>();
        for (Apartamento apartamento : apartamentosRepo.findAll()) {
            listaApartamentosDto.add(conversorFeign.apartamentoEntityToDto(apartamento));
        }
        return listaApartamentosDto;
    }
}
