package com.saguadopro.fotosms.services;

import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import com.saguadopro.fotosms.services.impl.FotoImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * Servicio encargado de codificar, decodificar y guardar imagens/foto deusuarios y apartamentos. Permite guardar y recuperar las imagenes/fotos del servidor
 */
@Service
public class FotoService implements FotoImpl {

    private static Logger log = Logger.getLogger(FotoService.class);

    /**
     * Metodo para la codificacion de imagenes. Recive una direccion en dónde se encuentra la imagen  y la codifica a BASE64
     * @param foto_url - Dirección donde se encuentra el archivo
     * @return String codificado en BASE64 de la imagen
     */
    @Override
    public String codificarFoto(String foto_url) {
        String fotoParaEnviar = "";
        try {
            BufferedImage fotoCodificada = ImageIO.read(new File(foto_url));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(fotoCodificada, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            fotoParaEnviar = Base64.getEncoder().encodeToString(imageBytes);
            bos.close();
            return fotoParaEnviar;
        } catch (IOException e) {
            log.error("Error al codificar foto");
            e.printStackTrace();
        }
        return fotoParaEnviar;
    }

    /**
     * Metodo para la decodificar una imagen. Recive una imagen codificada en BASE64 y devuelve un sarray de bytes de la imagen
     * @param fotoBase64 - String imagen codificada en BASE64
     * @return - Array de bytes de la imagen
     */
    @Override
    public byte[] decodificarFoto(String fotoBase64) {
        return Base64.getDecoder().decode(fotoBase64);
    }

    /**
     * Metodo para guardar una imagen en el servidor.  dependiendo del origen lo guarda en una carpeta u otra.
     * Convierte un array de bytes en un BufferedImage para guardarlla en un File
     * @param username - Usuario de la imagen
     * @param fotoBytes - Array de bytes de la imagen a guardar
     * @param origen - Desde que smicroervicio viene
     * @return - Url o direccion donde se almacenara la foto
     */
    @Override
    public String guardarFoto(String username, byte[] fotoBytes, String origen) {
        String foto_url = "";
        if (origen.equalsIgnoreCase("usuario")){
             foto_url= "src/main/resources/fotos/usuarios/foto_user_" + username + ".png";
        }else if (origen.equalsIgnoreCase("apartamento")){
            foto_url = "src/main/resources/fotos/apartamentos/foto_apart_" + username + ".png";
        }

        try {
            InputStream in = new ByteArrayInputStream(fotoBytes);
            BufferedImage fotoParaGuardar = ImageIO.read(in);
            fotoParaGuardar = resize(fotoParaGuardar,100,100);
            File outputfile = new File(foto_url);
            if (!outputfile.exists()){
                if (outputfile.createNewFile()) {
                    log.warn("Archivo creado de forma correcta al guardar imagen");
                } else {
                    log.error("Ha habido un problema al crear el archivo para guardar la imagen");
                }
            }
            ImageIO.write(fotoParaGuardar, "png", outputfile);
            return foto_url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foto_url;
    }


    /***
     * Metodo para escalar una imagen y que no ocupe mucho espacio en el servidor
     * @param img - BufferedImeage de la imagen a escalar
     * @param newW - Nuevo ancho de la imagen
     * @param newH - Nuevo alto de la imagen
     * @return - BufferedImage con el ancho y alto deseados
     */
    private BufferedImage resize(BufferedImage img, int newW, int newH) {
        ResampleOp resizeOp = new ResampleOp(newW, newH);
        resizeOp.setFilter(ResampleFilters.getLanczos3Filter());
        return resizeOp.filter(img, null);
    }
}
