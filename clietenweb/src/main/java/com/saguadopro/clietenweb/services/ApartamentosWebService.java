package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.PropietarioDTO;
import com.saguadopro.clietenweb.dto.TipoModeloDTO;
import com.saguadopro.clietenweb.feign.ApartamentosFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ApartamentosWebService {

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    private ApartamentosFeign apartamentosFeign;

    public ModelAndView crearNuevoApartamento(ApartamentoDTO apartamentoDTO, MultipartFile file, Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina", "pages/apartamentos/nuevo");
        try {
            byte[] fotoForm = file.getBytes();
            apartamentoDTO.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            if (apartamentosFeign.crearUsuarioFeign(apartamentoDTO) == HttpStatus.CREATED) {
                System.out.println("Apartamento creado");
                return vista;
            } else {
                //log4j
                System.out.println("Apartamento no creado");
                return vista;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vista;

    }


    public ModelAndView eliminarApartamento(String idApartamento, Principal principal, String origen) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        gestionarListaSegunOrigen(origen, vista, idApartamento);

        vista.addObject("pagina", "pages/apartamentos/"+origen);
        vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
        vista.addObject("listaTipoModelos", apartamentosFeign.listaTipoModelos());
        return vista;
    }
//TODO: comprobar si el buscar apartamento tambien actiualiza despues de modificar y ver la opcion de ELIMINAR
    public ModelAndView buscarApartamento(String idApartamento, Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina","pages/apartamentos/buscar");
        vista.addObject("lista", apartamentosFeign.buscarApartamento(idApartamento));
        vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
        vista.addObject("listaTipoModelos", apartamentosFeign.listaTipoModelos());
        return vista;
    }

    public ModelAndView modificarApartamento(ApartamentoDTO apartamentoDtoModificado, MultipartFile file, Principal principal, String origen) {
        ModelAndView vista  = inicioWebService.paginaInicioService(principal);
        try {
            byte[] fotoForm = file.getBytes();
            apartamentoDtoModificado.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            apartamentosFeign.modificarApartamento(apartamentoDtoModificado);
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaTipoModelos", apartamentosFeign.listaTipoModelos());
            vista.addObject("apartamentoFoto", "data:image/png;base64," + apartamentosFeign.buscarApartamento(String.valueOf(apartamentoDtoModificado.getIdApartamento())).get(0).getFoto());
            gestionarListaSegunOrigen(origen, vista, String.valueOf(apartamentoDtoModificado.getIdApartamento()));

            vista.addObject("pagina","pages/apartamentos/"+origen);
            return vista;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vista;
    }
    //TODO: ver porque no actualiza la lista al modificar
    public ModelAndView paginaListaApartamentosDisponibles(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
        List<ApartamentoDTO> listaDisponibles = new ArrayList<>();
        for (ApartamentoDTO apartamentoDTO : listaCompleta) {
            if (apartamentoDTO.getDisponible()) {
                listaDisponibles.add(apartamentoDTO);
            }
        }
        vista.addObject("lista", listaDisponibles);
        vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
        vista.addObject("listaTipoModelos", apartamentosFeign.listaTipoModelos());
        vista.addObject("pagina", "pages/apartamentos/disponibles");
        return vista;
    }

    public ModelAndView paginaListaApartamentosNoDisponibles(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
        List<ApartamentoDTO> listaNoDisponibles = new ArrayList<>();
        for (ApartamentoDTO apartamentoDTO : listaCompleta) {
            if (!apartamentoDTO.getDisponible()) {
                listaNoDisponibles.add(apartamentoDTO);
            }
        }
        vista.addObject("lista", listaNoDisponibles);
        vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
        vista.addObject("listaTipoModelos", apartamentosFeign.listaTipoModelos());
        vista.addObject("pagina", "pages/apartamentos/nodisponibles");
        return vista;
    }


    public List<PropietarioDTO> listaPropietarios() {
        return apartamentosFeign.listaPropietarios();
    }

    public PropietarioDTO buscarPropietario(String idPropietario) {
        PropietarioDTO propietarioDTO = apartamentosFeign.buscarPropietario(idPropietario);
        return propietarioDTO;
    }

    public List<TipoModeloDTO> listaTipoModelos() {
        return apartamentosFeign.listaTipoModelos();
    }

    public TipoModeloDTO buscarTipoModelo(String idTipoModelo) {
        TipoModeloDTO tipoModeloDTO = apartamentosFeign.buscarTipoModelo(idTipoModelo);
        return tipoModeloDTO;
    }

    private void gestionarListaSegunOrigen(String origen, ModelAndView vista, String idApartamento){

        if (origen.equals("disponibles")){
            List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
            List<ApartamentoDTO> listaDisponibles = new ArrayList<>();
            for (ApartamentoDTO apartamentoDTO : listaCompleta) {
                if (apartamentoDTO.getDisponible()) {
                    listaDisponibles.add(apartamentoDTO);
                }
            }
            vista.addObject("lista", listaDisponibles);
        }else if (origen.equals("nodisponibles")){
            List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
            List<ApartamentoDTO> listaNoDisponibles = new ArrayList<>();
            for (ApartamentoDTO apartamentoDTO : listaCompleta) {
                if (!apartamentoDTO.getDisponible()) {
                    listaNoDisponibles.add(apartamentoDTO);
                }
            }
            vista.addObject("lista", listaNoDisponibles);
        }else {
            vista.addObject("lista", apartamentosFeign.buscarApartamento(idApartamento));
        }
    }
}
