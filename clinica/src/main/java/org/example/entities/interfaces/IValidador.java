package org.example.entities.interfaces;

import org.example.entities.abstracts.ExameProcedimento;

public interface IValidador {
    public void validar(ExameProcedimento exame) throws Exception;

    public IValidador setProximo(IValidador validador);
}
