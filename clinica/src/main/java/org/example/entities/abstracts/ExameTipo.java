package org.example.entities.abstracts;

import java.util.List;

import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

public abstract class ExameTipo {
    private ILaudo laudo;
    //private List<INotificador> notificadores;
    private Paciente paciente;
    //private IValidador validador;
    private Object dados;
    private double precoBase;

    public abstract Object gerarLaudo(Object dados);
}
