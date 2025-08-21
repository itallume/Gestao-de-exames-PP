package org.example.entities;

import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.models.Paciente;

public class NotificadorSMS implements INotificador {
    @Override
    public void notificar(Paciente paciente, ILaudo laudo) {
        //TODO
    }
}
