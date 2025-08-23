package org.example.entities;

import java.util.List;

import org.example.entities.abstracts.ExameImagem;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorImagem;

public class ValidadorImplante extends ValidadorImagem {

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {

        if (exame instanceof ExameImagem) {
            System.out.println("Validando Implante");
            return true;
        } else {
            return this.proximo.validar(dados, exame);
        }
    }
}
