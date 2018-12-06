package com.saguadopro.clietenweb.feign;


import com.saguadopro.clietenweb.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Repository
@FeignClient("gestion-reservas")
public interface ReservasFeign {

    @RequestMapping(value = "/reservas",method = RequestMethod.GET)
    List<ReservaDTO> listaReservas();
}
