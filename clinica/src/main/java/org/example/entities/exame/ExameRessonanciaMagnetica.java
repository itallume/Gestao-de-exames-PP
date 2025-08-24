package org.example.entities.exame;

import org.example.entities.abstracts.ExameLaboratorial;
import org.example.entities.interfaces.ExameVisitor;

import com.itextpdf.layout.Document;

import com.itextpdf.layout.element.*;

import java.util.Map;

public class ExameRessonanciaMagnetica extends ExameLaboratorial {
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
        System.out.println("Realizando exame de Ressonancia Magnetica do paciente: " + getPaciente().getNome());
    }

    @Override
    public Document montarCorpoDocumento(Document doc, Map<String, String> dados) {
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

}
