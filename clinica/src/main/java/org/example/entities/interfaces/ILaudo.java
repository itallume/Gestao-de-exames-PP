package org.example.entities.interfaces;

import org.example.entities.abstracts.ExameProcedimento;

public interface ILaudo {
    Object gerarDocumento(ExameProcedimento exameTipo);
}
