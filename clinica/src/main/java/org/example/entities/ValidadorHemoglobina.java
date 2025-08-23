package org.example.entities;

import java.util.List;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorLaboratorial;

public class ValidadorHemoglobina extends ValidadorLaboratorial {

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {

        if (exame instanceof ExameSangue) {
            System.out.println("Validando Sangue");
            return true;
        } else {
            return this.proximo.validar(dados, exame);
        }
    }
}
