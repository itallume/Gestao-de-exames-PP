package org.example.entities.abstracts;

import java.util.List;

import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.interfaces.IValidador;
import org.example.entities.models.Paciente;

public abstract class ExameTipo {
    private ILaudo laudo;
    private List<INotificador> notificadores;
    private Paciente paciente;
    private Object dados;
    private double precoBase;

    public abstract void adicionarSubsribe(INotificador notificador);
    public abstract void removerSubsribe(INotificador notificador);
    public abstract void notificarTodos();
    public abstract Object gerarLaudo(Object dados);
}
