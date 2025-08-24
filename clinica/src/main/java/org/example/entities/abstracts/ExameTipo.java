package org.example.entities.abstracts;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.interfaces.IValidador;
import org.example.entities.models.Paciente;

@Data
public abstract class ExameTipo {
    private int id;
    private ILaudo laudo;
    private List<INotificador> notificadores;
    private Paciente paciente;
    private Object dados;
    private double precoBase;
    private Date dataRealizacao;

    public abstract void adicionarSubsribe(INotificador notificador);
    public abstract void removerSubsribe(INotificador notificador);
    public abstract void notificarTodos();
    public abstract Object gerarLaudo(Object dados);
    public abstract <T> T aceitar(ExameVisitor<T> visitor);
    public abstract void realizarExame();
}
