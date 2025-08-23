package org.example.entities.abstracts;

import java.util.List;

import org.example.entities.interfaces.IValidador;

public abstract class ValidadorImagem implements IValidador {
    protected IValidador proximo;

    @Override
    public IValidador setProximo(IValidador proximo) {
        this.proximo = proximo;
        return proximo;
    }

    @Override
    public abstract boolean validar(List<String> dados, ExameTipo exameImagem);
}
