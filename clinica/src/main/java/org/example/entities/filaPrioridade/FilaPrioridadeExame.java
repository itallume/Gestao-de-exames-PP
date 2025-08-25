package org.example.entities.filaPrioridade;

import org.example.entities.interfaces.InserirExameStrategy;
import org.example.entities.models.ExameOrdem;

import java.util.LinkedList;

public class FilaPrioridadeExame {

    private LinkedList<ExameOrdem> fila;

    public FilaPrioridadeExame() {
        this.fila = new LinkedList<>();
    }

    public void adicionarExame(ExameOrdem exame) {
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

    public ExameOrdem chamarProximo() throws Exception {
        if (fila.isEmpty()){
            throw new Exception("Fila vazia...");
        }

        ExameOrdem proximoExame = fila.getFirst();
        fila.removeFirst();
        System.out.println(this);
        return proximoExame;
    }

    public Boolean hasProximo(){
        return !fila.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fila de Exames:\n");

        int pos = 1;
        for (ExameOrdem e : fila) {
            String prioridadeSimbolo;
            switch (e.getPrioridade()) {
                case URGENTE -> prioridadeSimbolo = "⚠️ URGENTE";
                case POUCO_URGENTE -> prioridadeSimbolo = "🟢 POUCO URGENTE";
                case ROTINA -> prioridadeSimbolo = "🔵 ROTINA";
                default -> prioridadeSimbolo = e.getPrioridade().toString();
            }
            sb.append(pos).append(". Exame de id: ").append(e.getId())
                    .append(" Paciente: ").append(e.getPaciente().getNome()).append(" [").append(prioridadeSimbolo).append("]\n");
            pos++;
        }

        return sb.toString();
    }
}
