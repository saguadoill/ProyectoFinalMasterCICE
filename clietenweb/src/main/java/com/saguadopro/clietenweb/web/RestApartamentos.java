package com.saguadopro.clietenweb.web;


import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.PropietarioDTO;
import com.saguadopro.clietenweb.dto.CapacidadDTO;
import com.saguadopro.clietenweb.services.ApartamentosWebService;
import com.saguadopro.clietenweb.services.InicioWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Clase Controladora de los endpoints para la gestion de los apartamentos
 *
 * @see {@link ApartamentosWebService}
 */
@RestController
@RequestMapping(value = "/apartamentos")
public class RestApartamentos {

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    private ApartamentosWebService apartamentosWebService;


    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public ModelAndView eliminarApartamento(@RequestParam String idApartamento, @RequestParam String origen, Principal principal) {
        return apartamentosWebService.eliminarApartamento(idApartamento, principal, origen);
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.GET)
    public ModelAndView paginaNuevoApartamento(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("propietarios", apartamentosWebService.listaPropietarios());
        vista.addObject("listaCapacidades", apartamentosWebService.listaCapacidades());
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/apartamentos/nuevo");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    public ModelAndView crearApartamento(@RequestParam String direccion, @RequestParam String capacidad
            , @RequestParam String piso, @RequestParam String puerta, @RequestParam String propietario, @RequestParam MultipartFile file, Principal principal) {
        ApartamentoDTO apartamentoDTO = new ApartamentoDTO();
        apartamentoDTO.setDireccion(direccion);
        apartamentoDTO.setPiso(piso);
        apartamentoDTO.setPuerta(puerta);
        apartamentoDTO.setCapacidad(apartamentosWebService.buscarCapacidad(capacidad));
        apartamentoDTO.setPropietario(apartamentosWebService.buscarPropietario(propietario));
        System.out.println(apartamentoDTO);
        return apartamentosWebService.crearNuevoApartamento(apartamentoDTO, file, principal);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET)
    public ModelAndView paginaBuscarApartamento(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/apartamentos/buscar");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public ModelAndView buscarApartamento(@RequestParam String idApartamento, Principal principal) {
        return apartamentosWebService.buscarApartamento(idApartamento, principal);
    }

    @RequestMapping(value = "buscar/{idApartamento}", method = RequestMethod.GET)
    public ApartamentoDTO buscarApartamentoPorId(@PathVariable String idApartamento) {
        return apartamentosWebService.buscarApartamentoPorId(idApartamento);
    }

    @RequestMapping(value = "/disponibles", method = RequestMethod.GET)
    public ModelAndView paginaApartamentosDisponibles(Principal principal) {
        ModelAndView vista = apartamentosWebService.paginaApartamentosDisponibles(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/apartamentos/disponibles");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/nodisponibles", method = RequestMethod.GET)
    public ModelAndView paginaApartamentosNoDisponibles(Principal principal) {
        ModelAndView vista = apartamentosWebService.paginaApartamentosNoDisponibles(principal);
        if (inicioWebService.verificarCambioPasswd(principal)) {
            vista.addObject("pagina", "pages/apartamentos/nodisponibles");
        } else {
            vista.addObject("pagina", "pages/usuarios/passwd");
        }
        return vista;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    public ModelAndView modificarApartamento(@RequestParam String idApartamento, @RequestParam String direccion, @RequestParam String capacidad
            , @RequestParam String piso, @RequestParam String puerta, @RequestParam String propietario, @RequestParam String idHuesped, @RequestParam String disponible, @RequestParam MultipartFile file, @RequestParam String origen, Principal principal) {
        ApartamentoDTO apartamentoDtoModificado = new ApartamentoDTO();
        apartamentoDtoModificado.setIdApartamento(Long.parseLong(idApartamento));
        apartamentoDtoModificado.setDireccion(direccion);
        apartamentoDtoModificado.setPiso(piso);
        apartamentoDtoModificado.setPuerta(puerta);
        if (disponible.equalsIgnoreCase("true")) {
            apartamentoDtoModificado.setHuesped(null);
        } else {
            if (idHuesped.isEmpty()) {
                apartamentoDtoModificado.setHuesped(apartamentosWebService.buscarHuesped("0"));
            } else {
                apartamentoDtoModificado.setHuesped(apartamentosWebService.buscarHuesped(idHuesped));
            }
        }
        apartamentoDtoModificado.setCapacidad(apartamentosWebService.buscarCapacidad(capacidad));
        apartamentoDtoModificado.setPropietario(apartamentosWebService.buscarPropietario(propietario));
        return apartamentosWebService.modificarApartamento(apartamentoDtoModificado, file, principal, origen);
    }

    @RequestMapping(value = "/propietarios/{idPropietario}", method = RequestMethod.GET)
    public PropietarioDTO cargarPropietario(@PathVariable(value = "idPropietario") String idPropietario) {
        return apartamentosWebService.buscarPropietario(idPropietario);
    }

    @RequestMapping(value = "/tipos/{capacidad}", method = RequestMethod.GET)
    public CapacidadDTO cargarTipoModelo(@PathVariable(value = "capacidad") String capacidad) {
        return apartamentosWebService.buscarCapacidad(capacidad);
    }
}
