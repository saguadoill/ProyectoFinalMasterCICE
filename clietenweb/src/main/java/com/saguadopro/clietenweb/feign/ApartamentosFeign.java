package com.saguadopro.clietenweb.feign;


import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.PropietarioDTO;
import com.saguadopro.clietenweb.dto.CapacidadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Repository
@FeignClient("gestion-apartamentos")
public interface ApartamentosFeign {

    @RequestMapping(value = "/apartamentos", method = RequestMethod.POST)
    HttpStatus crearUsuarioFeign(ApartamentoDTO usuarioDTO);

    @RequestMapping(value = "/apartamentos/{idApartamento}",method = RequestMethod.DELETE)
    Boolean eliminarApartamentos(@PathVariable(value = "idApartamento") Long idApartamento);

    @RequestMapping(value = "/apartamentos",method = RequestMethod.PUT)
    HttpStatus modificarApartamento(@RequestBody ApartamentoDTO apartamentoDTOModificado);

    @RequestMapping(value = "/apartamentos/{idApartamento}", method = RequestMethod.GET)
    List<ApartamentoDTO> buscarApartamento(@PathVariable(value = "idApartamento") String idApartamento);

    @RequestMapping(value = "/apartamentos", method = RequestMethod.GET)
    List<ApartamentoDTO> listaApartamentos();

    @RequestMapping(value = "/apartamentos/propietarios",method = RequestMethod.GET)
    List<PropietarioDTO> listaPropietarios();

    @RequestMapping(value = "/apartamentos/propietarios/{idPropietario}",method = RequestMethod.GET)
    PropietarioDTO buscarPropietario(@PathVariable(value = "idPropietario") String idPropietario);

    @RequestMapping(value = "/apartamentos/capacidades",method = RequestMethod.GET)
    List<CapacidadDTO> listaCapacidades();

    @RequestMapping(value = "/apartamentos/capacidades/{capacidad}",method = RequestMethod.GET)
    CapacidadDTO buscarCapacidad(@PathVariable(value = "capacidad") String idCapacidad);

}
