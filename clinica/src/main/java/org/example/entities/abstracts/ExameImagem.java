package org.example.entities.abstracts;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class ExameImagem extends ExameProcedimento {
    @Override
    public abstract Object gerarLaudo();
}
