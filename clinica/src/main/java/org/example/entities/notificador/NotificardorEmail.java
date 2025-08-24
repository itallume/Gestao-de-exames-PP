package org.example.entities.notificador;

import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.models.Paciente;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificardorEmail implements INotificador {

    private final String username = "laboratoriostdiagnosticos@gmail.com";
    private final String password = "japa hbiu umvj vvhc";

    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @Override
    public void notificar(Paciente paciente, ILaudo laudo) throws MessagingException {
        Message message = new MimeMessage(getSession());
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(paciente.getEmail()));
        message.setSubject("Seu Laudo foi Emitido");
        message.setText("Olá "+ paciente.getNome() +"!\nO seu Laudo foi emitido!");

        Transport.send(message);
        System.out.println("Email enviado para o paciente " + paciente.getNome()+" com sucesso!");
    }
}
