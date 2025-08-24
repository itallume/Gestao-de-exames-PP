package org.example;


import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.laudo.LaudoTxt;
import org.example.entities.models.Paciente;
import org.example.entities.notificador.NotificardorEmail;

import javax.mail.MessagingException;

public class Main {
    public static void main(String[] args) throws MessagingException {
        Paciente paulo = new Paciente("Paulo", 21, "88888888888", "joao.azevedo.baia1@gmail.com", "Unimed", null);
        ILaudo laudo = new LaudoTxt();
        INotificador email = new NotificardorEmail();
        email.notificar(paulo, laudo);
    }
}