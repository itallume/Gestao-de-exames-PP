package org.example.entities;

import org.example.interfaces.IValidador;

public abstract class ValidadorImagem implements IValidador {
    @Override
    public IValidador setProximo(IValidador proximo) {
        return proximo;
    }
}
