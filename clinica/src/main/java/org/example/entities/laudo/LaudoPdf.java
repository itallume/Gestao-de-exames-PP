package org.example.entities.laudo;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
public class LaudoPdf implements ILaudo{
    @Override
    public Object gerarDocumento(ExameTipo exameTipo) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            Paciente paciente = exameTipo.getPaciente();
            Object dados = exameTipo.getDados();
            LocalDate dataHoje = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Cabeçalho
            Paragraph header = new Paragraph("Laboratório ST Diagnósticos")
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(ColorConstants.BLUE);
            doc.add(header);

            doc.add(new Paragraph("Paciente: " + paciente.getNome()));
            doc.add(new Paragraph("Convênio: " + paciente.getConvenio()));
            if (dados instanceof Map) {
                Map<String, Object> mapDados = (Map<String, Object>) dados;
                doc.add(new Paragraph("Médico Solicitante: " + mapDados.getOrDefault("medicoSolicitante", "—")));
            } else {
                doc.add(new Paragraph("Médico Solicitante: —"));
            }
            doc.add(new Paragraph("Data: " + dataHoje.format(formatter)));
            doc.add(new Paragraph("ID do Exame: " + exameTipo.getId()));

            doc.add(new Paragraph("\nResultado do Exame").setBold().setFontSize(14));

            // Corpo
            if (dados instanceof Map) {
                Map<String, Object> mapDados = (Map<String, Object>) dados;
                String tipo = (String) mapDados.getOrDefault("tipoExame", "Não especificado");
                doc.add(new Paragraph("Tipo de Exame: " + tipo));

                switch (tipo.toLowerCase()) {
                    case "sanguineo" -> {
                        Table tabela = new Table(UnitValue.createPercentArray(new float[]{4, 3, 6}));
                        tabela.setWidth(UnitValue.createPercentValue(100));
                        tabela.addHeaderCell("Exame");
                        tabela.addHeaderCell("Resultado");
                        tabela.addHeaderCell("Valores de Referência");

                        Map<String, String> resultados = (Map<String, String>) mapDados.get("resultados");
                        if (resultados != null) {
                            for (Map.Entry<String, String> entry : resultados.entrySet()) {
                                tabela.addCell(entry.getKey());
                                tabela.addCell(entry.getValue());
                                tabela.addCell((String) mapDados.getOrDefault("valoresReferencia", "—"));
                            }
                        }
                        doc.add(tabela);
                        doc.add(new Paragraph("Responsável técnico: " +
                                mapDados.getOrDefault("responsavelTecnico", "—")));
                    }
                    case "raiox" -> {
                        doc.add(new Paragraph("Laudo radiológico: " +
                                mapDados.getOrDefault("descricao", "—")));
                        doc.add(new Paragraph("Radiologista responsável: " +
                                mapDados.getOrDefault("radiologista", "—")));
                    }
                    case "ressonancia" -> {
                        doc.add(new Paragraph("Laudo de Ressonância: " +
                                mapDados.getOrDefault("descricao", "—")));
                        doc.add(new Paragraph("Protocolo: " +
                                mapDados.getOrDefault("protocolo", "—")));
                        doc.add(new Paragraph("Contraste utilizado: " +
                                mapDados.getOrDefault("contraste", "Não")));
                        doc.add(new Paragraph("Radiologista responsável: " +
                                mapDados.getOrDefault("radiologista", "—")));
                    }
                    default -> doc.add(new Paragraph("Dados do exame não especificados."));
                }
            } else {
                doc.add(new Paragraph("Dados do exame indisponíveis."));
            }

            // Rodapé
            doc.add(new Paragraph("\n"));
            Paragraph rodape = new Paragraph();
            if (exameTipo.getLaudo() != null && exameTipo.getDados() instanceof Map) {
                Map<String, Object> mapDados = (Map<String, Object>) exameTipo.getDados();
                rodape.add("Médico responsável: Dr(a). ")
                        .add((String) mapDados.getOrDefault("medicoResponsavel", "—"));
            } else {
                rodape.add("Médico responsável: —");
            }

            rodape.setTextAlignment(TextAlignment.CENTER).setMarginTop(30);
            doc.add(rodape);

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
