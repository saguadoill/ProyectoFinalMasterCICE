package com.saguadopro.gestioreservas.service;

import com.saguadopro.gestioreservas.conversor.Conversor;
import com.saguadopro.gestioreservas.entities.Reserva;
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

@Service
public class ReservasService  implements ReservasImpl {

    @Autowired
    ReservasRepo reservasRepo;

    @Override
    public List<ReservaDTO> listaReservas() {
        List<ReservaDTO> listaReservasDto = new ArrayList<>();
        for (Reserva reserva: reservasRepo.findAll()) {
            listaReservasDto.add(Conversor.reservaToDto(reserva));
        }
        return listaReservasDto;
    }

    @Override
    public Boolean eliminarReserva(ReservaDTO reservaDTO) {
        reservasRepo.delete(Conversor.dtoToReserva(reservaDTO));
        return null;
    }

    @Override
    public ReservaDTO buscarReserva(String idReserva) {
        return Conversor.reservaToDto(reservasRepo.findById(Long.parseLong(idReserva)).get());
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

