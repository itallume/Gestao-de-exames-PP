package org.example.entities.abstracts;

import org.example.entities.interfaces.IValidador;

public abstract class ValidadorLaboratorial implements IValidador {
    private IValidador proximo;

    @Override
    public IValidador setProximo(IValidador proximo) {
        this.proximo = proximo;
        return proximo;
    }
}
