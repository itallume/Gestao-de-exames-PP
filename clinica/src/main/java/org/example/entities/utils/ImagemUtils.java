package org.example.entities.utils;

import java.util.Base64;

public class ImagemUtils {

    public static String encodeImagem(byte[] imagemBytes) {
        return Base64.getEncoder().encodeToString(imagemBytes);
    }

    public byte[] decodeImagem(String base64Imagem) {
        return Base64.getDecoder().decode(base64Imagem);
    }
}
