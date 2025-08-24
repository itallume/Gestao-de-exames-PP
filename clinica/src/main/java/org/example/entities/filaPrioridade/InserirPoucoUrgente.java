package org.example.entities.filaPrioridade;

import org.example.Enum.Prioridade;
import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.Exame;

import java.util.LinkedList;

public class InserirPoucoUrgente implements InserirExameStrategy {
    @Override
    public void inserir(LinkedList<Exame> fila, Exame exame) {
        int i = 0;
        while (i < fila.size() && fila.get(i).getPrioridade() == Prioridade.URGENTE) {
            i++;
        }
        fila.add(i, exame);
    }
}
