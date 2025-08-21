package org.example.entities;

import java.util.List;

public class ValidadorPlaqueta extends ValidadorLaboratorial{
    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Plaqueta");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
