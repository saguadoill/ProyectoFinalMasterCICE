package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.repositories.PropietariosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import com.saguadopro.gestionapartamentos.services.impl.PropietariosImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para gestionar los Propietarios
 */
@Service
public class PropietariosService implements PropietariosImp {

    private final static Logger logger = Logger.getLogger(ApartamentosService.class);

    @Autowired
    private PropietariosRepo propietariosRepo;

    @Autowired
    private ConversorFeign conversorFeign;


    /**
     * Metodo para crear un Propietario y añadirlo  a la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param propietarioDTO - Dto de tipo Propietario
     * @return - True; si se ha creado de forma correcta. False; si no se ha podido crear
     */
    @Override
    public Boolean crearPropietario(PropietarioDTO propietarioDTO) {
        return null;
    }

    /**
     * Metodo para eliminar un Propietario de la BBDD.
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param idPropietario - Id del propietario a eliminar
     * @return - True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarPropietario(Long idPropietario) {
        return null;
    }

    /**
     * Metodo para modificar un propietario en la BBDD
     * @deprecated - Falta implementacion. No se usa en el codigo de momento
     * @param propietarioDTO - Dto de tipo Propietario
     * @return True; si se ha modificado de forma correcta. False; si no se ha podido modificar
     */
    @Override
    public Boolean modificarPropietario(PropietarioDTO propietarioDTO) {
        return null;
    }

    /**
     * Metodo para buscar un propietario en la BBDD
     * @param idPropietario - ID del propietario a buscar
     * @return - Dto de tipo Propietario
     */
    @Override
    public PropietarioDTO buscarPropietario(Integer idPropietario) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        try {
            if (propietariosRepo.findById(idPropietario).isPresent()){
                propietarioDTO = conversorFeign.propietarioEntityToDton(propietariosRepo.findById(idPropietario).get());
            }else {
                logger.warn("No hay ningun propietario con el id indicado");
            }
        }catch (Exception e){
            logger.error("No se ha podido realizar la busqueda del propietario en la BBDD: "+e.getMessage());
        }
        return propietarioDTO;
    }

    /**
     * Metodo que devuelve una lista completa de los propietarios de la BBDD
     * @return - Lista Dto de tipo Propietario
     */
    @Override
    public List<PropietarioDTO> listaPropietarios() {
        List<PropietarioDTO> listaApartamentosDto = new ArrayList<>();
        try {
            for (Propietario propietario : propietariosRepo.findAll()) {
                listaApartamentosDto.add(conversorFeign.propietarioEntityToDton(propietario));
            }
        }catch (Exception e){
            logger.error("No se ha podido recuperar la lista de propietarios de la BBDD: "+e.getMessage());
        }
        return listaApartamentosDto;
    }
}
