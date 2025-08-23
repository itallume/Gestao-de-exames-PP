package org.example.entities;

import java.util.List;

import org.example.entities.abstracts.ValidadorEndoscopico;
import org.example.entities.abstracts.ExameTipo;
public class ValidadorApendice extends ValidadorEndoscopico{

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {
        System.out.println("Validando Apendice");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
