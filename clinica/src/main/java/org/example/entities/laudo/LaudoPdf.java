package org.example.entities.laudo;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import com.itextpdf.layout.properties.TextAlignment;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoPdf implements ILaudo {
    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            Paciente paciente = exameTipo.getPaciente();
            Map<String, String> dados = exameTipo.getDados();
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
            doc.add(new Paragraph("Médico Solicitante: " + dados.getOrDefault("medicoSolicitante", "—")));
            doc.add(new Paragraph("Convênio: " + (paciente.getConvenio() != null ? paciente.getConvenio() : "—")));
            doc.add(new Paragraph("Data: " + dataHoje.format(formatter)));
            doc.add(new Paragraph("ID do Exame: " + exameTipo.getId()));
            doc.add(new Paragraph("\nResultado do Exame").setBold().setFontSize(14));

            // Corpo

            String tipo = (String) dados.getOrDefault("tipoExame", "Não especificado");
            doc.add(new Paragraph("Tipo de Exame: " + tipo));
            doc = exameTipo.montarPDF(doc, dados);

            // Rodapé
            doc.add(new Paragraph("\n"));
            Paragraph rodape = new Paragraph();
            if (exameTipo.getLaudo() != null && exameTipo.getDados() instanceof Map) {
                Map<String, String> mapDados = (Map<String, String>) exameTipo.getDados();
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
