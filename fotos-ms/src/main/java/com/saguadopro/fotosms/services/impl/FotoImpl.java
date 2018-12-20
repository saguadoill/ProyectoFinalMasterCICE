package com.saguadopro.fotosms.services.impl;

import java.awt.image.BufferedImage;

public interface FotoImpl {

    String codificarFoto(String foto_url);

    byte[] decodificarFoto(String foto);

    String guardarFoto(String username, byte[] fotoByte);

    BufferedImage resize(BufferedImage img, int newW, int newH);

}
