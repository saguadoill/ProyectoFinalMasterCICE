package com.saguadopro.gestionapartamentos.rest;

import com.saguadopro.gestionapartamentos.rest.dto.ApartamentoDTO;
import com.saguadopro.gestionapartamentos.rest.dto.PropietarioDTO;
import com.saguadopro.gestionapartamentos.rest.dto.TipoModeloDTO;
import com.saguadopro.gestionapartamentos.services.ApartamentosService;
import com.saguadopro.gestionapartamentos.services.PropietariosService;
import com.saguadopro.gestionapartamentos.services.TipoModelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionApartamentosRest {

    @Autowired
    ApartamentosService apartamentosService;

    @Autowired
    PropietariosService propietariosService;

    @Autowired
    TipoModelosService tipoModelosService;


    @RequestMapping(value = "/apartamentos",method = RequestMethod.POST)
    public HttpStatus crearApartamento(@RequestBody ApartamentoDTO apartamentoDTO){   // para probar con el postman @RequestBody
        if (apartamentosService.crearApartamento(apartamentoDTO)){
            return HttpStatus.CREATED;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/apartamentos/{idApartamento}",method = RequestMethod.DELETE)
    public Boolean eliminarApartamentos(@PathVariable(value = "idApartamento") Long idApartamento){
        if (apartamentosService.eliminarApartamento(idApartamento)){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping(value = "/apartamentos",method = RequestMethod.PUT)
    public HttpStatus modificarApartamento(@RequestBody ApartamentoDTO apartamentoDTOModificado){
        if (apartamentosService.modificarApartamento(apartamentoDTOModificado)){
            return HttpStatus.OK;
        }else{
            return HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/apartamentos/{idApartamento}", method = RequestMethod.GET)
    public List<ApartamentoDTO> buscarApartamento(@PathVariable(value = "idApartamento") String idApartamento){
        return apartamentosService.buscarApartamento(Long.parseLong(idApartamento));
    }

    @RequestMapping(value = "/apartamentos", method = RequestMethod.GET)
    public List<ApartamentoDTO> listaApartamentos(){
        return apartamentosService.listaApartamentos();
    }

    @RequestMapping(value = "/apartamentos/propietarios", method = RequestMethod.GET)
    public List<PropietarioDTO> listaPropietarios(){
        return propietariosService.listaPropietarios();
    }


    @RequestMapping(value = "/apartamentos/propietarios/{idPropietario}", method = RequestMethod.GET)
    public PropietarioDTO buscarPropietario(@PathVariable(value = "idPropietario") String idPropietario){
        return propietariosService.buscarPropietario(Integer.parseInt(idPropietario));
    }

    @RequestMapping(value = "/apartamentos/tipos", method = RequestMethod.GET)
    public List<TipoModeloDTO> listaTipoModelos(){
        return tipoModelosService.listaTiposModelos();
    }


    @RequestMapping(value = "/apartamentos/tipos/{idTipoModelo}", method = RequestMethod.GET)
    public TipoModeloDTO buscarTipoModelo(@PathVariable(value = "idTipoModelo") String idTipoModelo){
        return tipoModelosService.buscarTipoModelo(Integer.parseInt(idTipoModelo));
    }


}
