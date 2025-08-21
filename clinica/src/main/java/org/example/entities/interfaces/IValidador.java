package org.example.entities.interfaces;

import java.util.List;

public interface IValidador {
    public boolean validar(List<String> dados);
    public IValidador setProximo(IValidador validador);
}
