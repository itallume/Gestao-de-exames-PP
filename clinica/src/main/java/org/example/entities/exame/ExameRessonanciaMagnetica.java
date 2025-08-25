package org.example.entities.exame;

import org.example.entities.abstracts.ExameImagem;
import org.example.entities.interfaces.ExameVisitor;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.*;

import java.util.Map;

public class ExameRessonanciaMagnetica extends ExameImagem {
    @Override
    public void preencherDados(Map<String, String> dados) {
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
        System.out.println("Realizando exame de Ressonancia Magnetica do paciente: " + getPaciente().getNome());
    }

    @Override
    public Document montarPDF(Document doc, Map<String, String> dados) {
        doc.add(new Paragraph("Laudo de Ressonância: " +
                dados.getOrDefault("descricao", "—")));
        doc.add(new Paragraph("Protocolo: " +
                dados.getOrDefault("protocolo", "—")));
        doc.add(new Paragraph("Contraste utilizado: " +
                dados.getOrDefault("contraste", "Não")));
        doc.add(new Paragraph("Radiologista responsável: " +
                dados.getOrDefault("radiologista", "—")));
        return doc;
    }

    @Override
    public StringBuilder montarHtml(StringBuilder html, Map<String, String> dados) {
        html.append("<p>Laudo de Ressonância: ").append(dados.getOrDefault("descricao", "—")).append("</p>");
        html.append("<p>Protocolo: ").append(dados.getOrDefault("protocolo", "—")).append("</p>");
        html.append("<p>Contraste utilizado: ").append(dados.getOrDefault("contraste", "Não")).append("</p>");
        html.append("<p>Radiologista responsável: ").append(dados.getOrDefault("radiologista", "—"))
                .append("</p>");
        return html;
    }

    @Override
    public StringBuilder montarTxt(StringBuilder sb, Map<String, String> dados) {
        sb.append("Laudo de Ressonância Magnética: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Região examinada: ").append(dados.getOrDefault("regiao", "—")).append("\n");
        sb.append("Campo magnético: ").append(dados.getOrDefault("campo", "—")).append("\n");
        sb.append("Protocolo: ").append(dados.getOrDefault("protocolo", "—")).append("\n");
        sb.append("Sequências: ").append(dados.getOrDefault("sequencias", "—")).append("\n");
        sb.append("Contraste utilizado: ").append(dados.getOrDefault("contraste", "Não")).append("\n");
        if (dados.containsKey("dosagemContraste") && !dados.get("dosagemContraste").isEmpty()) {
            sb.append("Dosagem do contraste: ").append(dados.get("dosagemContraste")).append("\n");
        }
        sb.append("Radiologista responsável: ").append(dados.getOrDefault("radiologistaAss", "—")).append("\n");
        return sb;
    }

}
