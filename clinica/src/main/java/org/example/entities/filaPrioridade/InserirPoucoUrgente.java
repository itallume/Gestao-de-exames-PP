package org.example.entities.filaPrioridade;

import org.example.Enum.Prioridade;
import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.ExameOrdem;

import java.util.LinkedList;

public class InserirPoucoUrgente implements InserirExameStrategy {
    @Override
    public void inserir(LinkedList<ExameOrdem> fila, ExameOrdem exame) {
        int i = 0;
        while (i < fila.size() && (fila.get(i).getPrioridade() == Prioridade.URGENTE || fila.get(i).getPrioridade() == Prioridade.POUCO_URGENTE)) {
            i++;
        }
        fila.add(i, exame);
    }
}
