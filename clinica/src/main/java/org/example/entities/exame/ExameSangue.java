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
        System.out.println("Realizando exame de sangue do paciente: " + super.getPaciente().getNome());
    }

    @Override
    public Document montarPDF(Document doc, Map<String, String> dados) {
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

    @Override
    public StringBuilder montarHtml(StringBuilder html, Map<String, String> dados) {
        html.append("<table>");
        html.append("<tr><th>Exame</th><th>Resultado</th><th>Valores de Referência</th></tr>");

        String resultados = (String) dados.get("resultados");
        if (resultados != null) {
            html.append("<tr>");
            html.append("<td>Resultado</td>");
            html.append("<td>").append(resultados).append("</td>");
            html.append("<td>").append(dados.getOrDefault("valoresReferencia", "—")).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("<p>Responsável técnico: ").append(dados.getOrDefault("responsavelTecnico", "—"))
                .append("</p>");
        return html;
    }

    @Override
    public StringBuilder montarTxt(StringBuilder sb, Map<String, String> dados) {
        sb.append("=== Hemograma ===\n");
        sb.append("Hemoglobina: ").append(dados.getOrDefault("hemoglobina", "—")).append(" g/dL\n");
        sb.append("Hematócritos: ").append(dados.getOrDefault("hematocritos", "—")).append(" %\n");
        sb.append("Leucócitos: ").append(dados.getOrDefault("leucocitos", "—")).append(" /mm³\n");
        sb.append("Plaquetas: ").append(dados.getOrDefault("plaquetas", "—")).append(" /mm³\n");
        
        sb.append("\n=== Bioquímica ===\n");
        sb.append("Glicose: ").append(dados.getOrDefault("glicose", "—")).append(" mg/dL");
        if (dados.containsKey("glicoseDiagnostico")) {
            sb.append(" - ").append(dados.get("glicoseDiagnostico"));
        }
        sb.append("\n");
        
        sb.append("Colesterol Total: ").append(dados.getOrDefault("colesterol", "—")).append(" mg/dL");
        if (dados.containsKey("colesterolDiagnostico")) {
            sb.append(" - ").append(dados.get("colesterolDiagnostico"));
        }
        sb.append("\n");
        
        sb.append("Creatinina: ").append(dados.getOrDefault("creatinina", "—")).append(" mg/dL");
        if (dados.containsKey("creatininaDiagnostico")) {
            sb.append(" - ").append(dados.get("creatininaDiagnostico"));
        }
        sb.append("\n");
        
        sb.append("\nLaboratório: ").append(dados.getOrDefault("laboratorio", "—")).append("\n");
        sb.append("Responsável técnico: ").append(dados.getOrDefault("responsavelTecnico", "—")).append("\n");
        return sb;
    }

}
