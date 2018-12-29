package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Capacidad;
import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.repositories.CapacidadRepo;
import com.saguadopro.gestionapartamentos.rest.dto.CapacidadDTO;
import com.saguadopro.gestionapartamentos.services.impl.CapacidadImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para la gestion de las Capacidades
 */
@Service
public class CapacidadService implements CapacidadImp {

    private final static Logger logger = Logger.getLogger(CapacidadService.class);

    @Autowired
    private CapacidadRepo capacidadRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    /**
     * Metodo para crear una nueva capacidad y añadirla a la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param capacidadDTO - Dto de tipo Capacidad
     * @return - True; si se ha creado de forma correcta. False; si no se ha podido crear
     */
    @Override
    public Boolean crearCapacidad(CapacidadDTO capacidadDTO) {
        return null;
    }

    /**
     * Metodo para eliminar una capadidad de la BBDD.
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param idCapacidad - Id de la capacidad a eliminar
     * @return - True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarCapacidad(Integer idCapacidad) {
        return null;
    }

    /**
     * Metodo para modificar una capacidad en la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param capacidadDTO - Dto de tipo Capacidad
     * @return True; si se ha modificado de forma correcta. False; si no se ha podido modificar
     */
    @Override
    public Boolean modificarCapacidad(CapacidadDTO capacidadDTO) {
        return null;
    }

    /**
     * Metodo para buscar una capacidad en la BBDD
     * @param idCapacidad - ID de la capacidad a buscar
     * @return - Dto de tipo Capacidad
     */
    @Override
    public CapacidadDTO buscarPorCapacidad(Integer idCapacidad) {
        CapacidadDTO capacidadDTO = new CapacidadDTO();
        try {
            if (capacidadRepo.findById(idCapacidad).isPresent()){
                capacidadDTO = conversorFeign.capacidadEntityToDto(capacidadRepo.findById(idCapacidad).get());
            }else {
                logger.warn("No se ha encontrado ninguna capacidad");
            }
        }catch (Exception e){
            logger.error("No se ha podido buscar una Capacidad en la BBDD: "+e.getMessage());
        }
        return capacidadDTO;
    }

    /**
     * Metodo que devuelve una lista completa de las capacidades de la BBDD
     * @return - Lista Dto de tipo Capacidad
     */
    @Override
    public List<CapacidadDTO> listaCapacidades() {
        List<CapacidadDTO> listaTipoModelosDTO = new ArrayList<>();
       try {
           for (Capacidad capacidad : capacidadRepo.findAll()) {
               listaTipoModelosDTO.add(conversorFeign.capacidadEntityToDto(capacidad));
           }
       }catch (Exception e){
           logger.error("No se ha podido recuperar la lista de las capacidades de la BBDD: "+e.getMessage());
       }
        return listaTipoModelosDTO;
    }

}
