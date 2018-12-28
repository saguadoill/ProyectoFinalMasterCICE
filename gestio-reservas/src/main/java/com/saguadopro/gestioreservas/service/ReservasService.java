package com.saguadopro.gestioreservas.service;

import com.saguadopro.gestioreservas.entities.Reserva;
import com.saguadopro.gestioreservas.feign.ConversorFeign;
import com.saguadopro.gestioreservas.repositories.ReservasRepo;
import com.saguadopro.gestioreservas.rest.dto.ReservaDTO;
import com.saguadopro.gestioreservas.rest.dto.booking.com.Reservation;
import com.saguadopro.gestioreservas.rest.dto.booking.com.Reservations;
import com.saguadopro.gestioreservas.service.impl.ReservasImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservasService  implements ReservasImpl {

    @Autowired
    ReservasRepo reservasRepo;

    @Autowired
    ConversorFeign conversorFeign;

    @Override
    public List<ReservaDTO> listaReservas() {
        List<ReservaDTO> listaReservasDto = new ArrayList<>();
        for (Reserva reserva: reservasRepo.findAll()) {
            listaReservasDto.add(conversorFeign.reservaEntityToDto(reserva));
        }
        return listaReservasDto;
    }

    @Override
    public Boolean eliminarReserva(String idReserva) {
        reservasRepo.delete(conversorFeign.reservaDtoToEntity(buscarReserva(idReserva).get(0)));
        return null;
    }

    @Override
    public List<ReservaDTO> buscarReserva(String idReserva) {
        List<ReservaDTO> reservasDtoEncontradas = new ArrayList<>();
        List<Reserva> reservasEncontradas = new ArrayList<>();
        try {
            Long id = Long.parseLong(idReserva);
            reservasEncontradas.add(reservasRepo.findById(id).get());
            for (Reserva reserva : reservasEncontradas) {
                reservasDtoEncontradas.add(conversorFeign.reservaEntityToDto(reserva));
            }
            return reservasDtoEncontradas;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Boolean modificarReserva(ReservaDTO reservaDTO){
        try{
            Reserva reservaModificada = conversorFeign.reservaDtoToEntity(reservaDTO);
//            reservaOriginal.setEstaAsignada(reservaDTO.getEstaAsignada());
//            reservaOriginal.setTieneParking(reservaDTO.getTieneParking());
//            reservaOriginal.setNumero_personas(reservaDTO.getCapacidad());
//            reservaOriginal.setCliente(reservaDTO.getCliente());
//            reservaOriginal.setFechaEntrada(reservaDTO.getFechaEntrada());
//            reservaOriginal.setFechaSalida(reservaDTO.getFechaSalida());
//            reservaOriginal.setIdApartamento(reservaDTO.getIdApartamento());
            reservasRepo.save(reservaModificada);
            return true;
        }catch (Exception e){
            //log4j e.printStackTrace();
            e.printStackTrace();
            return false;
        }


    }

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
            e.printStackTrace();
        }
    }
}

