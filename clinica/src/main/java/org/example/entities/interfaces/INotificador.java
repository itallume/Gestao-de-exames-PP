package org.example.entities.interfaces;

import org.example.entities.models.Paciente;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface INotificador {

    void notificar(Paciente paciente, ILaudo laudo) throws MessagingException;
}
