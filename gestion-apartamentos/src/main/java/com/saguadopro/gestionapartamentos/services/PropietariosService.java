package com.saguadopro.gestionapartamentos.services;

import com.saguadopro.gestionapartamentos.entities.Propietario;
import com.saguadopro.gestionapartamentos.feign.ConversorFeign;
import com.saguadopro.gestionapartamentos.repositories.PropietariosRepo;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import com.saguadopro.gestionapartamentos.services.impl.PropietariosImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropietariosService implements PropietariosImp {

    @Autowired
    PropietariosRepo propietariosRepo;

    @Autowired
    ConversorFeign conversorFeign;


    @Override
    public Boolean crearPropietario(PropietarioDTO propietarioDTO) {
        return null;
    }

    @Override
    public Boolean eliminarPropietario(Long idPropietario) {
        return null;
    }

    @Override
    public Boolean modificarPropietario(PropietarioDTO propietarioDTO) {
        return null;
    }

    @Override
    public PropietarioDTO buscarPropietario(Integer idPropietario) {
        PropietarioDTO propietarioDTO = conversorFeign.propietarioEntityToDton(propietariosRepo.findById(idPropietario).get());
        return propietarioDTO;
    }

    @Override
    public List<PropietarioDTO> listaPropietarios() {
        List<PropietarioDTO> listaApartamentosDto = new ArrayList<>();
        for (Propietario propietario : propietariosRepo.findAll()) {
            listaApartamentosDto.add(conversorFeign.propietarioEntityToDton(propietario));
        }
        return listaApartamentosDto;
    }
}
