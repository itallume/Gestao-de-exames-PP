package org.example.entities.abstracts;

import lombok.Data;

@Data
public abstract class ExameEndoscopico extends ExameProcedimento{

    @Override
    public abstract Object gerarLaudo();
}
