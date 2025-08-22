package org.example.entities;

import org.example.entities.abstracts.ExameImagem;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.INotificador;

public class ExameRaioX extends ExameImagem{
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
