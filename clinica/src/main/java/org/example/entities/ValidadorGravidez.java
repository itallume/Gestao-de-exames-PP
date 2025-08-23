package org.example.entities;

import java.util.List;
import org.example.entities.abstracts.ValidadorEndoscopico;
import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.abstracts.ExameTipo;

public class ValidadorGravidez extends ValidadorEndoscopico {

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {

        if (exame instanceof ExameEndoscopico) {
            System.out.println("Validando Gravidez");
            return true;
        } else {
            return this.proximo.validar(dados, exame);
        }
    }
}
