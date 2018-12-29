package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.repositories.HuespedRepo;
import com.saguadopro.gestionapartamentos.rest.dto.HuespedDTO;
import com.saguadopro.gestionapartamentos.services.impl.HuespedImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestion de los Huespedes
 */
@Service
public class HuespedService implements HuespedImp {

    private final static Logger logger = Logger.getLogger(HuespedService.class);

    @Autowired
    private HuespedRepo huespedRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    /**
     * Metodo para crear un nuevo Huesped  y añadirlo a la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param huespedDTO - Dto de tipo Huesped
     * @return True; si se ha creado de forma correcta. False; si no se ha podido crear
     */
    @Override
    public Boolean crearHuesped(HuespedDTO huespedDTO) {
        return null;
    }

    /**
     * Metodo para eliminar un Huesped de la BBDD.
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param idHuesped - Id del huesped a eliminar
     * @return - True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarHuesped(Long idHuesped) {
        return null;
    }

    /**
     * Metodo para modificar un huesped en la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param huespedDTO - Dto de tipo Huesped
     * @return True; si se ha modificado de forma correcta. False; si no se ha podido modificar
     */
    @Override
    public Boolean modificarHuesped(HuespedDTO huespedDTO) {
        return null;
    }

    /**
     * Metodo para buscar un huesped en la BBDD
     * @param idHuesped - ID del huesped a buscar
     * @return - Dto de tipo Huesped
     */
    @Override
    public HuespedDTO buscarHuesped(Long idHuesped) {
        HuespedDTO huespedDTO  = new HuespedDTO();
        try {
            if (huespedRepo.findById(idHuesped).isPresent()){
                huespedDTO = conversorFeign.huespedEntityToDto(huespedRepo.findById(idHuesped).get());
            }
        }catch (Exception e){

        }
        return huespedDTO;
    }

    /**
     * Metodo que devuelve una lista completa de los huespedes de la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @return - Lista Dto de tipo Huesped
     */
    @Override
    public List<HuespedDTO> listaHuespedes() {
        return null;
    }
}
