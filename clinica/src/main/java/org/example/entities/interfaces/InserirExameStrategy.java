package org.example.entities.interfaces;

import org.example.entities.models.ExameOrdem;

import java.util.LinkedList;

public interface InserirExameStrategy {
    void inserir(LinkedList<ExameOrdem> fila, ExameOrdem exame);
}
