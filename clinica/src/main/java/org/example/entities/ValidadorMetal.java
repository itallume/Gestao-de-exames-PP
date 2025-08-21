package org.example.entities;

import java.util.List;

public class ValidadorMetal extends ValidadorImagem{

    @Override
    public boolean validar(List<String> dados) {
        System.out.println("Validando Metal");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
