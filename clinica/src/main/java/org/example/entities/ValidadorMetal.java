package org.example.entities;

import java.util.List;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorImagem;

public class ValidadorMetal extends ValidadorImagem{

    @Override
    public boolean validar(List<String> dados, ExameTipo exame) {
        System.out.println("Validando Metal");
        if (dados.isEmpty()) {
            return false;
        }
        return true; //implementar validação futuramente!!!
    }
}
