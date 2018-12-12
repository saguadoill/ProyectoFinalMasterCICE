package com.saguadopro.gestionusuarios.services;

import com.mortennobel.imagescaling.ResampleFilters;
import com.mortennobel.imagescaling.ResampleOp;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class GestionFotosService {


    public String codificarFoto(String foto_url) {
        System.out.println("URL de la foto: "+foto_url);
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

    public BufferedImage decodificarFoto(String foto) {
        BufferedImage fotoDecodificada = null;
        try {
            byte[] fotByte = Base64.getDecoder().decode(foto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fotByte);
            fotoDecodificada = ImageIO.read(byteArrayInputStream);
            byteArrayInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fotoDecodificada;
    }

    public String guardarFoto(String username, BufferedImage fotoParaGuardar) {
        fotoParaGuardar = resize(fotoParaGuardar,100,100);
        String foto_url = "src/main/resources/fotos/foto_" + username + ".png";
        try {
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

    public BufferedImage resize(BufferedImage img, int newW, int newH) {
//        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_AREA_AVERAGING);
//        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
//
//        Graphics2D g2d = dimg.createGraphics();
//        g2d.drawImage(tmp, 0, 0, null);
//        g2d.dispose();
        ResampleOp resizeOp = new ResampleOp(newW, newH);
        resizeOp.setFilter(ResampleFilters.getLanczos3Filter());

        return resizeOp.filter(img, null);
    }

//    boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
//        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
//            for (int x = 0; x < img1.getWidth(); x++) {
//                for (int y = 0; y < img1.getHeight(); y++) {
//                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
//                        return false;
//                }
//            }
//        } else {
//            return false;
//        }
//        return true;
//    }
}
