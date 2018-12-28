package com.saguadopro.fotosms.services.impl;

/**
 * Interface para la gestion de las imagenes/fotos
 * @see {@link com.saguadopro.fotosms.services.FotoService}
 */
public interface FotoImpl {

    String codificarFoto(String foto_url);

    byte[] decodificarFoto(String fotoBase64);

    String guardarFoto(String username, byte[] fotoBytes,String origen);

}
