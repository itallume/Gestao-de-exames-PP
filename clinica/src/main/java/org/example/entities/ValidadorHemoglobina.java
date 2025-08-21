package org.example.entities;

import java.util.List;

public class ValidadorHemoglobina extends ValidadorLaboratorial {
    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Hemoglobina");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
