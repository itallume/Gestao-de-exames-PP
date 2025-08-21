package org.example.entities;

import java.util.List;

import org.example.entities.abstracts.ValidadorImagem;

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
