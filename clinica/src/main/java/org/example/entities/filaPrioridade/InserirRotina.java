package org.example.entities.filaPrioridade;

import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.Exame;

import java.util.LinkedList;

public class InserirRotina implements InserirExameStrategy {

    @Override
    public void inserir(LinkedList<Exame> fila, Exame exame) {
        fila.addLast(exame);
    }
}
