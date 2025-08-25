package org.example.entities.exame;

import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.interfaces.ExameVisitor;

import com.itextpdf.layout.Document;

import java.util.Map;

public class ExameColonoscopia extends ExameEndoscopico {

    @Override
    public void preencherDados(Map<String,String> dados) {
        this.setDados(dados);
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
        System.out.println("Realizando exame de Colonoscopia do paciente: " + getPaciente().getNome());
    }

    @Override
    public Document montarPDF(Document doc, Map<String, String> dados) {
        doc.add(new com.itextpdf.layout.element.Paragraph("Laudo de Colonoscopia: " +
                dados.getOrDefault("descricao", "—")));
        doc.add(new com.itextpdf.layout.element.Paragraph("Endoscopista responsável: " +
                dados.getOrDefault("endoscopista", "—")));
        doc.add(new com.itextpdf.layout.element.Paragraph("Preparo Intestinal: " +
                dados.getOrDefault("preparoIntestinal", "Não informado")));
        return doc;
    }

    @Override
    public StringBuilder montarHtml(StringBuilder html, Map<String, String> dados) {
        html.append("<p>Laudo de Colonoscopia: ").append(dados.getOrDefault("descricao", "—")).append("</p>");
        html.append("<p>Endoscopista responsável: ").append(dados.getOrDefault("endoscopista", "—")).append("</p>");
        html.append("<p>Preparo Intestinal: ").append(dados.getOrDefault("preparoIntestinal", "Não informado"))
                .append("</p>");
        return html;
    }

    @Override
    public StringBuilder montarTxt(StringBuilder sb, Map<String, String> dados) {
        sb.append("Laudo de Colonoscopia: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Endoscopista responsável: ").append(dados.getOrDefault("endoscopista", "—")).append("\n");
        sb.append("Preparo Intestinal: ").append(dados.getOrDefault("preparoIntestinal", "Não informado")).append("\n");
        sb.append("Sedação: ").append(dados.getOrDefault("sedacao", "Não informada")).append("\n");
        sb.append("Achados: ").append(dados.getOrDefault("achados", "—")).append("\n");
        return sb;
    }

}
