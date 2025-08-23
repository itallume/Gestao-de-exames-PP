package org.example.entities;

import java.util.List;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.abstracts.ExameLaboratorial;

public class ValidadorPlaqueta extends ValidadorLaboratorial {

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {

        if (exame instanceof ExameLaboratorial) {
            System.out.println("Validando Plaqueta");
            return true;
        } else {
            return this.proximo.validar(dados, exame);
        }
    }

}
