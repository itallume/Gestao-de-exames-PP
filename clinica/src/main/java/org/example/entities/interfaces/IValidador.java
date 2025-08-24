package org.example.entities.interfaces;

import org.example.entities.abstracts.ExameTipo;

public interface IValidador {
    public void validar(ExameTipo exame) throws Exception;

    public IValidador setProximo(IValidador validador);
}
