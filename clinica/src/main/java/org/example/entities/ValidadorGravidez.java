package org.example.entities;

import java.util.List;

import org.example.entities.abstracts.ValidadorEndoscopico;

public class ValidadorGravidez extends ValidadorEndoscopico{

    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Gravidez");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
