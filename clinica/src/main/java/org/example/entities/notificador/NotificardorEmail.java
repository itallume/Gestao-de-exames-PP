package org.example.entities.notificador;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.models.Paciente;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        
        // Criar multipart para texto + anexo
        Multipart multipart = new MimeMultipart();
        
        // Parte do texto do email
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Olá " + paciente.getNome() + "!\n\n" +
                "O seu laudo foi emitido e está anexado neste email.\n\n" +
                "Atenciosamente,\n" +
                "Laboratório ST Diagnósticos");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        Transport.send(message);
        System.out.println("Email enviado para o paciente " + paciente.getNome() + " com sucesso!");
    }

    public void notificarComAnexo(Paciente paciente, ILaudo laudo, ExameProcedimento exame) throws MessagingException {
        try {
            // Gerar o laudo
            Object laudoConteudo = laudo.gerarDocumento(exame);
            
            // Criar arquivo temporário com o laudo
            File arquivoLaudo = criarArquivoLaudo(paciente, laudoConteudo);
            
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(paciente.getEmail()));
            message.setSubject("Seu Laudo foi Emitido - " + paciente.getNome());
            
            // Criar multipart para texto + anexo
            Multipart multipart = new MimeMultipart();
            
            // Parte do texto do email
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Olá " + paciente.getNome() + "!\n\n" +
                    "O seu laudo foi emitido e está anexado neste email.\n\n" +
                    "Atenciosamente,\n" +
                    "Laboratório ST Diagnósticos");
            multipart.addBodyPart(messageBodyPart);
            
            // Parte do anexo
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(arquivoLaudo);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(arquivoLaudo.getName());
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Email com laudo anexado enviado para " + paciente.getNome() + " com sucesso!");
            
            // Limpar arquivo temporário
            arquivoLaudo.delete();
            
        } catch (IOException e) {
            throw new MessagingException("Erro ao criar arquivo de laudo: " + e.getMessage(), e);
        }
    }
    
    private File criarArquivoLaudo(Paciente paciente, Object laudoConteudo) throws IOException {
        // Criar diretório temp se não existir
        File tempDir = new File("temp");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        
        // Criar arquivo com nome único
        String nomeArquivo = "Laudo_" + paciente.getNome().replaceAll("\\s+", "_") + "_" + System.currentTimeMillis() + ".txt";
        File arquivo = new File(tempDir, nomeArquivo);
        
        // Escrever conteúdo no arquivo
        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.write(laudoConteudo.toString());
        }
        
        return arquivo;
    }
}
