package com.saguadopro.conversorms.services;

import com.saguadopro.conversorms.entities.*;
import com.saguadopro.conversorms.feign.FotosFeign;
import com.saguadopro.conversorms.rest.dto.*;
import com.saguadopro.conversorms.services.impl.EntityToDtoImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de convertir Entidades a Dto.
 */
@Service
public class EntityToDtoService implements EntityToDtoImpl {

    private static Logger logger = Logger.getLogger(EntityToDtoService.class);

    @Autowired
    private FotosFeign fotosFeign;

    /**
     * Convierte un objeto Usuario de Entidad (@see {@link Usuario}) a Dto (@see {@link UsuarioDTO})
     * @param usuario - Entidad de tipo Usuario
     * @return - Dto de tipo Usuario
     */
    public UsuarioDTO usuarioEntityToDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        try {
            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setUsername(usuario.getUsername());
            usuarioDTO.setPerfil(perfilEntityToDto(usuario.getPerfil()));
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setApellidos(usuario.getApellidos());
            usuarioDTO.setPasswd(usuario.getPasswd());
            usuarioDTO.setCambioPasswd(usuario.getCambioPasswd());
            usuarioDTO.setFoto(fotosFeign.codificarFoto(usuario.getFoto_url()));
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Usuario a DTO: "+e.getMessage());
        }
        return usuarioDTO;
    }

    /**
     * Convierte un objeto Perfil de Entidad (@see {@link Perfil}) a Dto {@link PerfilDTO})
     * @param perfil - Entidad de tipo Perfil
     * @return - Dto de tipo Perfil
     */
    public PerfilDTO perfilEntityToDto(Perfil perfil){
        PerfilDTO perfilDTO = new PerfilDTO();
        try {
            perfilDTO.setIdPerfil(perfil.getIdPerfil());
            perfilDTO.setNombrePerfil(perfil.getNombrePerfil());
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Perfil a DTO: "+e.getMessage());
        }
        return perfilDTO;
    }

    /**
     * Convierte un objeto de tipo Apartamento de Entidad (@see {@link Apartamento}) a Dto (@see {@link ApartamentoDTO})
     * @param apartamento - Entidad de tipo Apartamento
     * @return - Dto de tipo Apartamento
     */
    @Override
    public ApartamentoDTO apartamentoEntityToDto(Apartamento apartamento) {
        ApartamentoDTO apartamentoDTO = new ApartamentoDTO();
        try {
            apartamentoDTO.setIdApartamento(apartamento.getIdApartamento());
            apartamentoDTO.setCapacidad(capacidadEntityToDto(apartamento.getCapacidad()));
            apartamentoDTO.setDireccion(apartamento.getDireccion());
            apartamentoDTO.setPropietario(propietarioEntityToDton(apartamento.getPropietario()));
            apartamentoDTO.setPiso(apartamento.getPiso());
            apartamentoDTO.setPuerta(apartamento.getPuerta());
            if (apartamento.getHuesped() != null) {
                apartamentoDTO.setHuesped(huespedEntityToDto(apartamento.getHuesped()));
            }
            apartamentoDTO.setFoto(fotosFeign.codificarFoto(apartamento.getFoto_url()));
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Apartamento a DTO: "+e.getMessage());
        }
        return apartamentoDTO;
    }

    /**
     * Convierte un objeto de tipo Capacidad de Entidad (@see {@link Capacidad} a Dto (@see {@link CapacidadDTO}))
     * @param  - Entidad de tipo Capacidad
     * @return - Dto de tipo Capacidad
     */
    @Override
    public CapacidadDTO capacidadEntityToDto(Capacidad capacidad) {
        CapacidadDTO capacidadDTO = new CapacidadDTO();
        try {
            capacidadDTO.setIdCapacidad(capacidad.getIdCapacidad());
            capacidadDTO.setMaxPersonas(capacidad.getMaxPersonas());
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Capacidad a DTO: "+e.getMessage());
        }
        return capacidadDTO;
    }

    /**
     * Convierte un objeto de tipo Huesped de Entidad (@see {@link Huesped} a Dto (@see {@link HuespedDTO}))
     * @param huesped - Entidad de tipo Huesped
     * @return - Dto de tipo Huesped
     */
    @Override
    public HuespedDTO huespedEntityToDto(Huesped huesped) {
        HuespedDTO huespedDTO = new HuespedDTO();
        try {
            huespedDTO.setIdHuesped(huesped.getIdHuesped());
            huespedDTO.setNombre(huesped.getNombre());
            huespedDTO.setApellidos(huesped.getApellidos());
            huespedDTO.setTelefono(huesped.getTelefono());
            huespedDTO.setEmail(huesped.getEmail());
            huespedDTO.setDni(huesped.getDni());
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Huesped a DTO: "+e.getMessage());
        }
        return huespedDTO;
    }

    /**
     * Convierte un objeto de tipo Propietario de Entidad (@see {@link Propietario} a Dto (@see {@link PropietarioDTO}))
     * @param propietario - Entidad de tipo Propietario
     * @return - Dto de tipo Propietario
     */
    @Override
    public PropietarioDTO propietarioEntityToDton(Propietario propietario) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        try {
            propietarioDTO.setIdPropietario(propietario.getIdPropietario());
            propietarioDTO.setNombre(propietario.getNombre());
            propietarioDTO.setApellidos(propietario.getApellidos());
            propietarioDTO.setTelefono(propietario.getTelefono());
            propietarioDTO.setEmail(propietario.getEmail());
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Propietario a DTO: "+e.getMessage());
        }
        return propietarioDTO;
    }

    /**
     * Convierte un objeto de tipo Reserva de Entidad (@see {@link Reserva} a Dto (@see {@link ReservaDTO}))
     * @param  - Entidad de tipo Reserva
     * @return - Dto de tipo Reserva
     */
    @Override
    public ReservaDTO reservaEntityToDto(Reserva reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        try {
            reservaDTO.setIdReserva(reserva.getIdReserva());
            reservaDTO.setCliente(reserva.getCliente());
            reservaDTO.setFechaEntrada(reserva.getFechaEntrada());
            reservaDTO.setFechaSalida(reserva.getFechaSalida());
            reservaDTO.setIdApartamento(reserva.getIdApartamento());
            reservaDTO.setCapacidad(reserva.getNumero_personas());
            reservaDTO.setTieneParking(reserva.getTieneParking());
            reservaDTO.setEstaAsignada(reserva.getEstaAsignada());
            reservaDTO.setPrecioTotal(reserva.getPrecioTotal());
        }catch (Exception e){
            logger.error("No se ha podido convertir una Entidad Reserva a DTO: "+e.getMessage());
        }
        return reservaDTO;
    }
}
