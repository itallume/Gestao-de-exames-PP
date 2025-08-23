package org.example.entities;

import java.util.List;
import org.example.entities.models.Exame;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.ExameSangue;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.abstracts.ExameLaboratorial;

public class ValidadorHemoglobina extends ValidadorLaboratorial {
    
    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {
        if (exame instanceof ExameLaboratorial){
            return false;
        }
        System.out.println("Validando Hemoglobina");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
