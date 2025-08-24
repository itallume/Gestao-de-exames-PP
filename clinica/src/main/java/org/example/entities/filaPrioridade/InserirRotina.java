package org.example.entities.filaPrioridade;

import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.ExameOrdem;

import java.util.LinkedList;

public class InserirRotina implements InserirExameStrategy {

    @Override
    public void inserir(LinkedList<ExameOrdem> fila, ExameOrdem exame) {
        fila.addLast(exame);
    }
}
