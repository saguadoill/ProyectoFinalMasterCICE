package com.saguadopro.fotosms.services;

import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import com.saguadopro.fotosms.services.impl.FotoImpl;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

@Service
public class FotoService implements FotoImpl {

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
            e.printStackTrace();
        }
        return fotoParaEnviar;
    }

    @Override
    public byte[] decodificarFoto(String foto) {
        return Base64.getDecoder().decode(foto);
    }

    @Override
    public String guardarFoto(String username, byte[] fotoBytes) {
        String foto_url = "src/main/resources/fotos/foto_" + username + ".png";
        try {
            InputStream in = new ByteArrayInputStream(fotoBytes);
            BufferedImage fotoParaGuardar = ImageIO.read(in);
            fotoParaGuardar = resize(fotoParaGuardar,100,100);

            File outputfile = new File(foto_url);
            if (outputfile.createNewFile()) {
                System.out.println("Log4j: archivo creado de forma correcta");
            } else {
                System.out.println("Log4j: ha habido algun problema a la hora de crear el archivo");
            }
            ImageIO.write(fotoParaGuardar, "png", outputfile);
            return foto_url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foto_url;
    }

    @Override
    public BufferedImage resize(BufferedImage img, int newW, int newH) {
        ResampleOp resizeOp = new ResampleOp(newW, newH);
        resizeOp.setFilter(ResampleFilters.getLanczos3Filter());
        return resizeOp.filter(img, null);
    }
}
