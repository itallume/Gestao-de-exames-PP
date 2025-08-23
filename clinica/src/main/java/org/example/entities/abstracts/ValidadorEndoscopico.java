package org.example.entities.abstracts;

import org.example.entities.interfaces.IValidador;
import java.util.List;

public abstract class ValidadorEndoscopico implements IValidador {
    private IValidador proximo;

    @Override
    public IValidador setProximo(IValidador proximo) {
        this.proximo = proximo;
        return proximo;
    }

    @Override
    public abstract boolean validar(List<String> dados, ExameTipo exameEndoscopico);
}
