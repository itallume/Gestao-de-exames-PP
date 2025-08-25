package org.example.entities.abstracts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.INotificador;
import org.example.entities.models.Paciente;

import com.itextpdf.layout.Document;

import javax.mail.MessagingException;

@Data
@NoArgsConstructor
public abstract class ExameProcedimento{
    private int id;
    private ILaudo laudo;
    private List<INotificador> notificadores;
    private Paciente paciente;
    private double precoBase;
    private Date dataRealizacao;
    private Map<String, String> dados;

    public void adicionarSubsribe(INotificador notificador){
        notificadores.add(notificador);
    }

    public void removerSubsribe(INotificador notificador){
        notificadores.remove(notificador);
    }

    public void notificarTodos() throws MessagingException {
        for (INotificador n : notificadores) {
            n.notificar(paciente, laudo);
        }
    }

    public abstract void preencherDados(Map<String, String> dados);
    public abstract Object gerarLaudo();
    public abstract <T> T aceitar(ExameVisitor<T> visitor);
    public abstract void realizarExame();
    public abstract Document montarPDF(Document doc, Map<String, String> dados);
    public abstract StringBuilder montarHtml(StringBuilder html, Map<String, String> dados);
    public abstract StringBuilder montarTxt(StringBuilder sb, Map<String, String> dados);
}
