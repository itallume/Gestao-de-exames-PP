package org.example.entities.exame;

import lombok.Data;
import org.example.entities.abstracts.ExameImagem;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.INotificador;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.util.Map;

@Data
public class ExameRaioX extends ExameImagem {

    @Override
    public void preencherDados(Map<String, String> dados) {

    }

    @Override
    public Object gerarLaudo() {
        return null;
    }

    @Override
    public <T> T aceitar(ExameVisitor<T> visitor) {
        return visitor.visitarExame(this);
    }

    @Override
    public void realizarExame() {
        System.out.println("Realizando exame de RaioX do paciente: " + getPaciente().getNome());
    }

    @Override
    public Document montarPDF(Document doc, Map<String, String> dados) {
        doc.add(new Paragraph("Laudo radiológico: " +
                dados.getOrDefault("descricao", "—")));
        doc.add(new Paragraph("Radiologista responsável: " +
                dados.getOrDefault("radiologista", "—")));
        return doc;
    }

    @Override
    public StringBuilder montarHtml(StringBuilder html, Map<String, String> dados) {
        html.append("<p>Laudo radiológico: ").append(dados.getOrDefault("descricao", "—")).append("</p>");
        html.append("<p>Radiologista responsável: ").append(dados.getOrDefault("radiologista", "—"))
                .append("</p>");
        return html;

    }

    @Override
    public StringBuilder montarTxt(StringBuilder sb, Map<String, String> dados) {
        sb.append("Laudo radiológico: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Radiologista responsável: ").append(dados.getOrDefault("radiologista", "—")).append("\n");
        return sb;
    }

}
