package org.example.entities.exame;

import org.example.entities.abstracts.ExameLaboratorial;
import org.example.entities.interfaces.ExameVisitor;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.UnitValue;

import java.util.Map;

public class ExameSangue extends ExameLaboratorial {

    public ExameSangue() {
        super();
    }

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
        System.out.println("Realizando exame de sangue do paciente: " + super.getPaciente().getNome());
    }

    @Override
    public Document montarCorpoDocumento(Document doc, Map<String, String> dados) {
        Table tabela = new Table(UnitValue.createPercentArray(new float[] { 4, 3, 6 }));
        tabela.setWidth(UnitValue.createPercentValue(100));
        tabela.addHeaderCell("Exame");
        tabela.addHeaderCell("Resultado");
        tabela.addHeaderCell("Valores de Referência");

        String resultados = dados.get("resultados");
        if (resultados != null) {
            tabela.addCell("Resultado");
            tabela.addCell(resultados);
            tabela.addCell((String) dados.getOrDefault("valoresReferencia", "—"));
        }
        doc.add(tabela);
        doc.add(new Paragraph("Responsável técnico: " +
                dados.getOrDefault("responsavelTecnico", "—")));

        return doc;
    }

}
