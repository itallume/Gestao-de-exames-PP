package org.example.entities;

import org.example.entities.abstracts.ExameLaboratorial;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.INotificador;

public class ExameSangue extends ExameLaboratorial {
    @Override
    public void adicionarSubsribe(INotificador notificador) {

    }

    @Override
    public void removerSubsribe(INotificador notificador) {

    }

    @Override
    public void notificarTodos() {

    }

    @Override
    public Object gerarLaudo(Object dados) {
        return null;
    }

    @Override
    public <T> T aceitar(ExameVisitor<T> visitor) {
        return visitor.visitarExame(this);
    }
}
