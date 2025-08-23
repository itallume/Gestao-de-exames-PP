package org.example.entities;

import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorEndoscopico;

import java.util.List;

public class ValidadorApendice extends ValidadorEndoscopico {

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {

        if (exame instanceof ExameEndoscopico) {
            System.out.println("Validando Apendice");
            return true;
        } else {
            return this.proximo.validar(dados, exame);
        }
    }

}
