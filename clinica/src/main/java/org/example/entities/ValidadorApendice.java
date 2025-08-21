package org.example.entities;

import java.util.List;

public class ValidadorApendice extends ValidadorEndoscopico{

    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Apendice");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
