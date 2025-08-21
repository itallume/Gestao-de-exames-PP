package org.example.entities.interfaces;

import org.example.entities.models.Paciente;

public interface INotificador {

    void notificar(Paciente paciente, ILaudo laudo);
}
