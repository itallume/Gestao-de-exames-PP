package org.example.entities.interfaces;

import org.example.entities.models.Exame;

import java.util.LinkedList;

public interface InserirExameStrategy {
    void inserir(LinkedList<Exame> fila, Exame exame);
}
