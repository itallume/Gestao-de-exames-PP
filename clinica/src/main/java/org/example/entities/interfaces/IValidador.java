package org.example.entities.interfaces;

import org.example.entities.models.Exame;
import org.example.entities.abstracts.ExameTipo;
import java.util.List;

public interface IValidador {
    public boolean validar(List<String> dados, ExameTipo exame);
    public IValidador setProximo(IValidador validador);
}
