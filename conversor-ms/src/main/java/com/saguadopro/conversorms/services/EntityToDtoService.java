package com.saguadopro.conversorms.services;

import com.saguadopro.conversorms.entities.*;
import com.saguadopro.conversorms.feign.FotosFeign;
import com.saguadopro.conversorms.rest.dto.*;
import com.saguadopro.conversorms.services.impl.EntityToDtoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de convertir Entidades a Dto.
 */
@Service
public class EntityToDtoService implements EntityToDtoImpl {

    @Autowired
    FotosFeign fotosFeign;

    /**
     * Convierte un objeto Usuario de Entidad (@see {@link Usuario}) a Dto (@see {@link UsuarioDTO})
     * @param usuario - Entidad de tipo Usuario
     * @return - Dto de tipo Usuario
     */
    public UsuarioDTO usuarioEntityToDto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPerfil(perfilEntityToDto(usuario.getPerfil()));
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellidos(usuario.getApellidos());
        usuarioDTO.setPasswd(usuario.getPasswd());
        usuarioDTO.setCambioPasswd(usuario.getCambioPasswd());
        usuarioDTO.setFoto(fotosFeign.codificarFoto(usuario.getFoto_url()));
        return usuarioDTO;
    }

    /**
     * Convierte un objeto Perfil de Entidad (@see {@link Perfil}) a Dto {@link PerfilDTO})
     * @param perfil - Entidad de tipo Perfil
     * @return - Dto de tipo Perfil
     */
    public PerfilDTO perfilEntityToDto(Perfil perfil){
        PerfilDTO perfilDTO = new PerfilDTO();
        perfilDTO.setIdPerfil(perfil.getIdPerfil());
        perfilDTO.setNombrePerfil(perfil.getNombrePerfil());
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
        capacidadDTO.setIdCapacidad(capacidad.getIdCapacidad());
        capacidadDTO.setMaxPersonas(capacidad.getMaxPersonas());
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
        huespedDTO.setIdHuesped(huesped.getIdHuesped());
        huespedDTO.setNombre(huesped.getNombre());
        huespedDTO.setApellidos(huesped.getApellidos());
        huespedDTO.setTelefono(huesped.getTelefono());
        huespedDTO.setEmail(huesped.getEmail());
        huespedDTO.setDni(huesped.getDni());
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
        propietarioDTO.setIdPropietario(propietario.getIdPropietario());
        propietarioDTO.setNombre(propietario.getNombre());
        propietarioDTO.setApellidos(propietario.getApellidos());
        propietarioDTO.setTelefono(propietario.getTelefono());
        propietarioDTO.setEmail(propietario.getEmail());
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
        reservaDTO.setIdReserva(reserva.getIdReserva());
        reservaDTO.setCliente(reserva.getCliente());
        reservaDTO.setFechaEntrada(reserva.getFechaEntrada());
        reservaDTO.setFechaSalida(reserva.getFechaSalida());
        reservaDTO.setIdApartamento(reserva.getIdApartamento());
        reservaDTO.setCapacidad(reserva.getNumero_personas());
        reservaDTO.setTieneParking(reserva.getTieneParking());
        reservaDTO.setEstaAsignada(reserva.getEstaAsignada());
        reservaDTO.setPrecioTotal(reserva.getPrecioTotal());
        return reservaDTO;
    }
}
