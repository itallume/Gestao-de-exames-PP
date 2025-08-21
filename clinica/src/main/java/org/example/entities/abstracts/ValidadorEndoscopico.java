package org.example.entities.abstracts;

import org.example.entities.interfaces.IValidador;

public abstract class ValidadorEndoscopico implements IValidador {
    
    @Override
    public IValidador setProximo(IValidador proximo) {
        return proximo;
    }
}
