package org.example.entities;

import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.Exame;

import java.util.LinkedList;

public class FilaPrioridadeExame {

    private LinkedList<Exame> fila;

    public void adicionarExame(Exame exame) {
        InserirExameStrategy strategy;
        switch (exame.getPrioridade()) {
            case URGENTE -> strategy = new InserirUrgente();
            case POUCO_URGENTE -> strategy = new InserirPoucoUrgente();
            case ROTINA -> strategy = new InserirRotina();
            default -> throw new IllegalArgumentException("Prioridade desconhecida");
        }
        strategy.inserir(fila, exame);
        System.out.println(this);
    }

    public Exame chamarProximo() throws Exception {
        if (fila.isEmpty()){
            throw new Exception("Fila vazia...");
        }

        Exame proximoExame = fila.getFirst();
        fila.removeFirst();
        return proximoExame;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fila de Exames:\n");

        int pos = 1;
        for (Exame e : fila) {
            String prioridadeSimbolo;
            switch (e.getPrioridade()) {
                case URGENTE -> prioridadeSimbolo = "⚠️ URGENTE";
                case POUCO_URGENTE -> prioridadeSimbolo = "🟢 POUCO URGENTE";
                case ROTINA -> prioridadeSimbolo = "🔵 ROTINA";
                default -> prioridadeSimbolo = e.getPrioridade().toString();
            }
            sb.append(pos).append(". Exame de id: ").append(e.getId())
                    .append(" [").append(prioridadeSimbolo).append("]\n");
            pos++;
        }

        return sb.toString();
    }
}
