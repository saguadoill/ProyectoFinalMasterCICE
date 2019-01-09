package com.saguadopro.gestioreservas.service;

import com.saguadopro.gestioreservas.entities.Reserva;
import com.saguadopro.gestioreservas.feign.ConversorFeign;
import com.saguadopro.gestioreservas.repositories.ReservasRepo;
import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import com.saguadopro.gestioreservas.rest.dto.booking.com.Reservation;
import com.saguadopro.gestioreservas.rest.dto.booking.com.Reservations;
import com.saguadopro.gestioreservas.service.impl.ReservasImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la gestion de las reservas
 */
@Service
public class ReservasService  implements ReservasImpl {

    private final static Logger logger = Logger.getLogger(ReservasService.class);

    @Autowired
    private ReservasRepo reservasRepo;

    @Autowired
    private ConversorFeign conversorFeign;

    /**
     * Metodo que devuelve una lista completa de todoas las reservas de la BBDD
     * @return Lista de Dto tipo Reserva
     */
    @Override
    public List<ReservaDTO> listaReservas() {
        List<ReservaDTO> listaReservasDto = new ArrayList<>();
        try {
            for (Reserva reserva: reservasRepo.findAll()) {
                listaReservasDto.add(conversorFeign.reservaEntityToDto(reserva));
            }
        }catch (Exception e){
            logger.error("No se ha podido obtener la lista de reservas: "+e.getMessage());
        }
        return listaReservasDto;
    }

    /**
     * Metodo que elimina una Reserva de la BBDD
     * @param idReserva - Id o numero de identificacion de la reserva a eliminar
     * @return True; si se ha eliminado de forma correcta. False; si no se ha podido eliminar
     */
    @Override
    public Boolean eliminarReserva(String idReserva) {
        Reserva reservaParaEliminar = new Reserva();
        try {
            if (buscarReserva(idReserva).get(0) != null){
                reservaParaEliminar = conversorFeign.reservaDtoToEntity(buscarReserva(idReserva).get(0));
                reservasRepo.delete(reservaParaEliminar);
                return true;
            }else {
                logger.error("La reserva  a eliminar no existe en la BBDD");
                return false;
            }
        }catch (Exception e){
            logger.error("No s eha podido eliminar la reserva de la BBDD: "+e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para buscar reservas por id
     * @param idReserva - Id de la reserva a buscar
     * @return - Lista de Dto tipo Reserva
     */
    @Override
    public List<ReservaDTO> buscarReserva(String idReserva) {
        List<ReservaDTO> reservasDtoEncontradas = new ArrayList<>();
        Reserva reservaBuscada;
        try {
            if (reservasRepo.findById(Long.parseLong(idReserva)).isPresent()){
                reservaBuscada = reservasRepo.findById(Long.parseLong(idReserva)).get();
                reservasDtoEncontradas.add(conversorFeign.reservaEntityToDto(reservaBuscada));
                return reservasDtoEncontradas;
            }else{
                logger.error("La reserva a Buscar no existe en la BBDD");
                return null;
            }
        } catch (Exception e) {
            logger.error("No se ha podido buscar la reserva en la BBDD: "+e.getMessage());
            return null;
        }
    }

    /**
     * Metodo para modificar los datos de una Reserva en la BBDD
     * @param reservaDTO - Dto de tipo Reserva para modificar
     * @return True; si se ha modificado de forma correcta. False; si no se ha podido modificar
     */
    public Boolean modificarReserva(ReservaDTO reservaDTO){
        try{
            Reserva reservaModificada = conversorFeign.reservaDtoToEntity(reservaDTO);
            reservasRepo.save(reservaModificada);
            return true;
        }catch (Exception e){
            logger.error("No se ha podido modificar la reserva en la BBDD: "+e.getMessage());
            return false;
        }
    }

    /**
     * Metodo para parsear una reserva de Booking que llega como response en XML a una Resrva de nuestra BBDD
     * @param xmlFile - ResponseBody del xml a parsear
     */
    public void getBookinReservas(String xmlFile){
        Reservations reservations;
        try {
            InputStream inputStream = new ByteArrayInputStream(xmlFile.getBytes());
            JAXBContext jaxbContext = JAXBContext.newInstance(Reservations.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            reservations = (Reservations) unmarshaller.unmarshal(inputStream);
            System.out.println(reservations);
            for (Reservation reservation : reservations.getListaReservas()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(reservation.getId());
                reserva.setIdApartamento(reservation.getRoom().getId());
                reserva.setCliente(reservation.getCustomer().getFirst_name()+" "+reservation.getCustomer().getLast_name());
                reserva.setNumero_personas(reservation.getRoom().getNumberofguests());
                reserva.setFechaEntrada(LocalDate.parse(reservation.getRoom().getArrival_date()));
                reserva.setFechaSalida(LocalDate.parse(reservation.getRoom().getDeparture_date()));
                reserva.setPrecioTotal(reservation.getRoom().getTotalprice());
                reservasRepo.save(reserva);
            }
        } catch (JAXBException e) {
            logger.warn("Las reserva procedentes de Booking.com no se han podido parsear: "+e.getErrorCode());
        }
    }
}

