package org.example.entities;

import org.example.interfaces.IValidador;

abstract class ValidadorLaboratorial implements IValidador {

    @Override
    public IValidador setProximo(IValidador proximo) {
        return proximo;
    }
}
