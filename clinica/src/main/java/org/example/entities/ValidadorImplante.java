package org.example.entities;

import java.util.List;

public class ValidadorImplante extends ValidadorImagem{

    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Implante");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
