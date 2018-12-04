package com.saguadopro.clietenweb.web;


import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.PropietarioDTO;
import com.saguadopro.clietenweb.dto.TipoModeloDTO;
import com.saguadopro.clietenweb.services.ApartamentosWebService;
import com.saguadopro.clietenweb.services.InicioWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
@RequestMapping(value = "/apartamentos")
public class RestApartamentos {

    @Autowired
    InicioWebService inicioWebService;

    @Autowired
    ApartamentosWebService apartamentosWebService;


    @RequestMapping(value = "/eliminar",method = RequestMethod.DELETE)
    public ModelAndView eliminarApartamento(@RequestParam String idApartamento ,@RequestParam String origen, Principal principal) {
        return apartamentosWebService.eliminarApartamento(idApartamento,principal,origen);
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public ModelAndView paginaNuevoApartamento(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina","pages/apartamentos/nuevo");
        vista.addObject("propietarios",apartamentosWebService.listaPropietarios());
        vista.addObject("tipos",apartamentosWebService.listaTipoModelos());
        return vista;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public ModelAndView crearApartamento(@RequestParam String direccion,@RequestParam String tipo,@RequestParam String capacidad
            ,@RequestParam String piso,@RequestParam String puerta,@RequestParam String propietario, @RequestParam MultipartFile file, Principal principal) {
        ApartamentoDTO apartamentoDTO = new ApartamentoDTO();
        apartamentoDTO.setDireccion(direccion);
        apartamentoDTO.setCapacidad(Integer.parseInt(capacidad));
        apartamentoDTO.setPiso(piso);
        apartamentoDTO.setPuerta(puerta);
        apartamentoDTO.setDisponible(true);
        apartamentoDTO.setTipo(apartamentosWebService.buscarTipoModelo(tipo));
        apartamentoDTO.setPropietario(apartamentosWebService.buscarPropietario(propietario));
        System.out.println(apartamentoDTO);
        return apartamentosWebService.crearNuevoApartamento(apartamentoDTO,file,principal) ;
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView paginaBuscarApartamento(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina","pages/apartamentos/buscar");
        return vista;
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscarApartamento(@RequestParam String idApartamento, Principal principal) {
        return apartamentosWebService.buscarApartamento(idApartamento,principal);
    }

    @RequestMapping(value = "/disponibles", method = RequestMethod.GET)
    public ModelAndView paginaApartamentosDisponibles(Principal principal) {
        return apartamentosWebService.paginaListaApartamentosDisponibles(principal);
    }

    @RequestMapping(value = "/nodisponibles", method = RequestMethod.GET)
    public ModelAndView paginaApartamentosNoDisponibles(Principal principal) {
        return apartamentosWebService.paginaListaApartamentosNoDisponibles(principal);
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public ModelAndView modificarApartamento(@RequestParam String idApartamento,@RequestParam String direccion,@RequestParam String tipo,@RequestParam String capacidad
            ,@RequestParam String piso,@RequestParam String puerta,@RequestParam String propietario,@RequestParam String disponible, @RequestParam MultipartFile file,@RequestParam String origen ,Principal principal) {
        ApartamentoDTO apartamentoDtoModificado = new ApartamentoDTO();
        apartamentoDtoModificado.setIdApartamento(Long.parseLong(idApartamento));
        apartamentoDtoModificado.setDireccion(direccion);
        apartamentoDtoModificado.setCapacidad(Integer.parseInt(capacidad));
        apartamentoDtoModificado.setPiso(piso);
        apartamentoDtoModificado.setPuerta(puerta);
        System.out.println("modificar: disponible: "+disponible);
        apartamentoDtoModificado.setDisponible(Boolean.parseBoolean(disponible.toUpperCase()));
        System.out.println("modificar: disponible: "+apartamentoDtoModificado.getDisponible());
        apartamentoDtoModificado.setTipo(apartamentosWebService.buscarTipoModelo(tipo));
        apartamentoDtoModificado.setPropietario(apartamentosWebService.buscarPropietario(propietario));
        return apartamentosWebService.modificarApartamento(apartamentoDtoModificado,file,principal,origen);
    }

    @RequestMapping(value = "/propietarios/{idPropietario}", method = RequestMethod.GET)
    public PropietarioDTO cargarPropietario(@PathVariable(value = "idPropietario") String idPropietario){
        return apartamentosWebService.buscarPropietario(idPropietario);
    }

    @RequestMapping(value = "/tipos/{idTipoModelo}", method = RequestMethod.GET)
    public TipoModeloDTO cargarTipoModelo(@PathVariable(value = "idTipoModelo") String idTipoModelo){
        return apartamentosWebService.buscarTipoModelo(idTipoModelo);
    }
}
