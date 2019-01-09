package com.saguadopro.clietenweb.services;

import com.saguadopro.clietenweb.dto.ApartamentoDTO;
import com.saguadopro.clietenweb.dto.HuespedDTO;
import com.saguadopro.clietenweb.dto.PropietarioDTO;
import com.saguadopro.clietenweb.dto.CapacidadDTO;
import com.saguadopro.clietenweb.feign.ApartamentosFeign;
import org.apache.log4j.Logger;
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

/**
 * Servicio encargado de las páginas para la gestion de los apartamentos
 */
@Service
public class ApartamentosWebService {

    private static Logger logger = Logger.getLogger(ApartamentosWebService.class);

    @Autowired
    private InicioWebService inicioWebService;

    @Autowired
    private ApartamentosFeign apartamentosFeign;

    /**
     * Metodo para la creacion de nuevos apartamentos en la BBDD
     *
     * @param apartamentoDTO - Dto tipo Apartamento
     * @param file           - Foto o imagen del apartamento
     * @param principal      - Usuario logado
     * @return - Vista para volver a crear un nuevo apartamento desde cero
     */
    public ModelAndView crearNuevoApartamento(ApartamentoDTO apartamentoDTO, MultipartFile file, Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        vista.addObject("pagina", "pages/apartamentos/nuevo");
        try {
            byte[] fotoApartamento = file.getBytes();
            apartamentoDTO.setFoto(Base64.getEncoder().encodeToString(fotoApartamento));
            if (apartamentosFeign.crearApartamento(apartamentoDTO) == HttpStatus.CREATED) {
                logger.info("El apartamento se ha creado de forma correcta");
                return vista;
            } else {
                logger.error("No se ha podido crear el apartamento. Consultar ApartamentosMicroservice para mas informacion");
                return vista;
            }
        } catch (IOException e) {
            logger.error("Error al convertir la foto o imagen en bytes: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Metodo que elimina un apartamento de la BBDD
     *
     * @param idApartamento - Id del apartamento a eliminar
     * @param principal     - Usuario logado
     * @param origen        - Nombre desde dónde se envia la solicitud de eliminacion
     * @return - Vista de la página desde donde se realiza la peticion
     */
    public ModelAndView eliminarApartamento(String idApartamento, Principal principal, String origen) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        try {
            vista.addObject("lista", gestionarListaSegunOrigen(origen, idApartamento));
            vista.addObject("pagina", "pages/apartamentos/" + origen);
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaCapacidades", apartamentosFeign.listaCapacidades());
            apartamentosFeign.eliminarApartamentos(idApartamento);
        } catch (Exception e) {
            logger.error("No se ha podido eliminar el apartamento de la BBDD. Comprobar ApartamentosMicroservice para mas info: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Metodo que busca un apartamento en la BBDD y lo muestra en la página web
     *
     * @param idApartamento - Id del apartamento a buscar
     * @param principal     - Usuario logado
     * @return - Vista de la página de busqueda actualizada con el resultado
     */
    public ModelAndView buscarApartamento(String idApartamento, Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        try {
            vista.addObject("pagina", "pages/apartamentos/buscar");
            vista.addObject("lista", apartamentosFeign.buscarApartamento(idApartamento));
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaCapacidades", apartamentosFeign.listaCapacidades());
        } catch (Exception e) {
            logger.error("No se ha podido buscar un apartamento en la BBDD. Comprobar ApartamentosMicroservice para mas info: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Metodo que busca un apartamento por el ID en la BBDD
     *
     * @param idApartamento - ID del apartamento a buscar
     * @return - Dto tipo Apartamento
     */
    public ApartamentoDTO buscarApartamentoPorId(String idApartamento) {
        ApartamentoDTO apartamentoDtoEncontrado = new ApartamentoDTO();
        try {
            apartamentoDtoEncontrado = apartamentosFeign.buscarApartamento(idApartamento).get(0);
        } catch (Exception e) {
            logger.error("No se ha podido buscar el apartamento. Comprobar ApartaemtnosMicroservice para mas info: " + e.getMessage());
        }
        return apartamentoDtoEncontrado;
    }

    /**
     * Metodo que modifica los datos de un apartamento
     *
     * @param apartamentoDtoModificado - Dto tipo Apartamento
     * @param file                     - Foto o imagen del apartamento
     * @param principal                - Usuario Logado
     * @param origen                   - Nombre desde donde se llama al metodo
     * @return - Vista de la página desde donde se llama al método actualizada
     */
    public ModelAndView modificarApartamento(ApartamentoDTO apartamentoDtoModificado, MultipartFile file, Principal principal, String origen) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        try {
            byte[] fotoForm = file.getBytes();
            apartamentoDtoModificado.setFoto(Base64.getEncoder().encodeToString(fotoForm));
            apartamentosFeign.modificarApartamento(apartamentoDtoModificado);
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaCapacidades", apartamentosFeign.listaCapacidades());
            vista.addObject("apartamentoFoto", "data:image/png;base64," + apartamentosFeign.buscarApartamento(String.valueOf(apartamentoDtoModificado.getIdApartamento())).get(0).getFoto());
            vista.addObject("lista", gestionarListaSegunOrigen(origen, String.valueOf(apartamentoDtoModificado.getIdApartamento())));
            vista.addObject("pagina", "pages/apartamentos/" + origen);
            return vista;
        } catch (IOException e) {
            logger.error("Error al convertir en bytes la imagen del formulario: " + e.getMessage());
        } catch (Exception e) {
            logger.error("No se ha podido cargar la página de modificacion de apartamentos: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Método que carga la página de apartamentos que están disponibles
     *
     * @param principal - Usuario logado
     * @return - Vista con los apartamentos disponibles
     */
    public ModelAndView paginaApartamentosDisponibles(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        try {
            List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
            List<ApartamentoDTO> listaDisponibles = new ArrayList<>();
            for (ApartamentoDTO apartamentoDTO : listaCompleta) {

                if (apartamentoDTO.getHuesped() == null) {
                    listaDisponibles.add(apartamentoDTO);
                    System.out.println(apartamentoDTO);
                }
            }
            vista.addObject("lista", listaDisponibles);
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaCapacidades", apartamentosFeign.listaCapacidades());
        } catch (Exception e) {
            logger.error("No se ha podido cargar la página de Apartamentos Disponibles: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Método que carga la página de apartamentos que NO están disponibles
     *
     * @param principal - Usuario logado
     * @return - Vista con los apartamentos no disponibles
     */
    public ModelAndView paginaApartamentosNoDisponibles(Principal principal) {
        ModelAndView vista = inicioWebService.paginaInicioService(principal);
        try {
            List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
            List<ApartamentoDTO> listaNoDisponibles = new ArrayList<>();
            for (ApartamentoDTO apartamentoDTO : listaCompleta) {
                if (apartamentoDTO.getHuesped() != null) {
                    listaNoDisponibles.add(apartamentoDTO);
                }
            }
            vista.addObject("lista", listaNoDisponibles);
            vista.addObject("listaPropietarios", apartamentosFeign.listaPropietarios());
            vista.addObject("listaCapacidades", apartamentosFeign.listaCapacidades());
        } catch (Exception e) {
            logger.error("No se ha podido cargar la página de Apartamentos No Disponibles: " + e.getMessage());
        }
        return vista;
    }

    /**
     * Metodo que devuelve una lista de todos los propietarios existentes en la BBDD
     *
     * @return - Lista de Dto tipo Propietario
     */
    public List<PropietarioDTO> listaPropietarios() {
        return apartamentosFeign.listaPropietarios();
    }

    /**
     * Método para buscar un propietario en la BBDD
     *
     * @param idPropietario - Id del propietario a buscar
     * @return - Dto tipo Propietario
     */
    public PropietarioDTO buscarPropietario(String idPropietario) {
        return apartamentosFeign.buscarPropietario(idPropietario);
    }

    /**
     * Metodo que devuelve una lista de todas las capacidades existentes en la BBDD
     *
     * @return - Lista de Dto tipo Capacidad
     */
    public List<CapacidadDTO> listaCapacidades() {
        return apartamentosFeign.listaCapacidades();
    }

    /**
     * Método para buscar una capacidad en la BBDD
     *
     * @param idCapacidad - Id de la capacidad a buscar
     * @return - Dto tipo Capacidad
     */
    public CapacidadDTO buscarCapacidad(String idCapacidad) {
        return apartamentosFeign.buscarCapacidad(idCapacidad);
    }

    /**
     * Método para buscar un huesped en la BBDD
     *
     * @param idHuesped - Id del huesped a buscar
     * @return - Dto tipo Capacidad
     */
    public HuespedDTO buscarHuesped(String idHuesped) {
        return apartamentosFeign.buscarHuesped(idHuesped);
    }

    /**
     * Método que devuelve una lista de los apartamentos segun la página que lo pida
     *
     * @param origen        - Página de la solicitud
     * @param idApartamento - Id del apartamento en caso de busqueda. Puede ser null.
     * @return - Lista de Dto tipo Apartamento
     */
    public List<ApartamentoDTO> gestionarListaSegunOrigen(String origen, String idApartamento) {
        List<ApartamentoDTO> listaCompleta = apartamentosFeign.listaApartamentos();
        List<ApartamentoDTO> listaFiltrada = new ArrayList<>();
        try {
            if (origen.equalsIgnoreCase("disponibles")) {
                for (ApartamentoDTO apartamentoDTO : listaCompleta) {
                    if (apartamentoDTO.getHuesped() == null) {
                        listaFiltrada.add(apartamentoDTO);
                    }
                }
            } else if (origen.equalsIgnoreCase("nodisponibles")) {
                for (ApartamentoDTO apartamentoDTO : listaCompleta) {
                    if (apartamentoDTO.getHuesped() != null) {
                        listaFiltrada.add(apartamentoDTO);
                    }
                }
            } else {
                listaFiltrada = apartamentosFeign.buscarApartamento(idApartamento);
            }
        } catch (Exception e) {
            logger.error("No se ha podido gestionar la lista segun el origen: "+e.getMessage());
        }
        return listaFiltrada;
    }
}
