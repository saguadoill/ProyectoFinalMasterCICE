package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Apartamento;
import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.feign.FotosFeign;
import com.saguadopro.gestionapartamentos.repositories.ApartamentosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.services.impl.ApartamentosImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de la gestion de los Apartamentos
 */
@Service
public class ApartamentosService implements ApartamentosImp {

    private final static Logger logger = Logger.getLogger(ApartamentosService.class);

    @Autowired
    private ApartamentosRepo apartamentosRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    @Autowired
    private FotosFeign fotosFeign;

    /**
     * Metodo para crear un Apartamento y añadirlo  a la BBDD
     * @param apartamentoDTO - Dto de tipo Apartamento
     * @return - True; si se ha creado de forma correcta. False; si no se ha podido crear
     */
    @Override
    public Boolean crearApartamento(ApartamentoDTO apartamentoDTO) {
        try {
            if (apartamentoDTO != null) {
                Apartamento apartamentoBBDD = conversorFeign.apartamentoDtoToEntity(apartamentoDTO);
                apartamentosRepo.save(apartamentoBBDD);
                return true;
            } else {
                logger.error("Datos del apartamento enviados estan null o vacios");
                return false;
            }
        }catch (Exception e){
            logger.error("No se ha podido crear un apartamento: "+e.getMessage());
            return false;
        }

    }

    /**
     * Metodo que elimina un Apartamento de la BBDD
     * @param idApartamento - Id o numero de identificacion del apartamento a eliminar
     * @return True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarApartamento(Long idApartamento) {
        Apartamento apartamentoParaEliminar = apartamentosRepo.findById(idApartamento).get();
        try {
            apartamentosRepo.delete(apartamentoParaEliminar);
            return true;
        } catch (Exception e) {
            logger.error("Apartamento no se ha podido eliminar:" + e.getMessage());
        }
        return false;
    }

    /**
     * Metodo para modificar los datos de un Apartamento den la BBDD
     * @param apartamentoDTOModificado - Dto de tipo Apartamento para modificar
     * @return True; si se ha modificado de forma correcta. False; si no se ha podido modificar
     */
    @Override
    public Boolean modificarApartamento(ApartamentoDTO apartamentoDTOModificado) {
        try {
            Optional<Apartamento> apartamentoOriginal = apartamentosRepo.findById(apartamentoDTOModificado.getIdApartamento());
            System.out.println("Apartamneto Original: "+apartamentoOriginal.toString());
            if (apartamentoOriginal.isPresent()) {
                if (!apartamentoOriginal.get().getCapacidad().getIdCapacidad().equals(apartamentoDTOModificado.getCapacidad().getIdCapacidad())) {
                    apartamentoOriginal.get().setCapacidad(conversorFeign.capacidadDtoToEntity(apartamentoDTOModificado.getCapacidad()));
                }
                if (!apartamentoOriginal.get().getDireccion().equals(apartamentoDTOModificado.getDireccion())) {
                    apartamentoOriginal.get().setDireccion(apartamentoDTOModificado.getDireccion());
                }
                if (!apartamentoOriginal.get().getPropietario().getIdPropietario().equals(apartamentoDTOModificado.getPropietario().getIdPropietario())) {
                    apartamentoOriginal.get().setPropietario(conversorFeign.propietarioDtoToEntity(apartamentoDTOModificado.getPropietario()));
                }
                if (!apartamentoOriginal.get().getPiso().equals(apartamentoDTOModificado.getPiso())) {
                    apartamentoOriginal.get().setPiso(apartamentoDTOModificado.getPiso());
                }
                if (!apartamentoOriginal.get().getPuerta().equalsIgnoreCase(apartamentoDTOModificado.getPuerta())) {
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
                logger.error("El apartamento original que hay que modificar, no existe o no es accesible");
                return false;
            }
        } catch (Exception e) {
            logger.error("No se ha podido modificar el apartamento: "+e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para buscar apartamentos por id
     * @param idApartamento - Id del apartamento a buscar
     * @return - Lista de Dto tipo Apartamento
     */
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
           logger.error("Ha habido un error a la hora de buscar un apartamento: "+e.getMessage());
           return null;
       }
    }

    /**
     * Metodo que devuelve una lista completa de todos los apartamentos de la BBDD
     * @return Lista de Dto tipo Apartamento
     */
    @Override
    public List<ApartamentoDTO> listaApartamentos() {
        List<ApartamentoDTO> listaApartamentosDto = new ArrayList<>();
        try {
            for (Apartamento apartamento : apartamentosRepo.findAll()) {
                listaApartamentosDto.add(conversorFeign.apartamentoEntityToDto(apartamento));
            }
        }catch (Exception e){
            logger.error("Error al intentar recuperar la lista de los apartamentos de la BBDD: "+e.getMessage());
        }
        return listaApartamentosDto;
    }
}
