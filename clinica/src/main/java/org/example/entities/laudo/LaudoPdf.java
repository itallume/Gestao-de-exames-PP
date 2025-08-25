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
import org.example.entities.exame.*;

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

            Map<String, Object> dadosLaudo = exameTipo.getDadosParaLaudo();
            Paciente paciente = (Paciente) dadosLaudo.get("paciente");
            @SuppressWarnings("unchecked")
            Map<String, String> dados = (Map<String, String>) dadosLaudo.get("dadosBasicos");
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
            doc.add(new Paragraph("Idade: " + paciente.getIdade() + " anos"));
            doc.add(new Paragraph("Sexo: " + paciente.getSexo()));
            doc.add(new Paragraph("Convênio: " + paciente.getConvenio()));
            doc.add(new Paragraph("Telefone: " + paciente.getTelefone()));
            doc.add(new Paragraph("Médico Solicitante: " + dados.getOrDefault("medicoSolicitante", "—")));
            doc.add(new Paragraph("Data: " + dataHoje.format(formatter)));
            doc.add(new Paragraph("ID do Exame: " + dadosLaudo.get("id")));
            doc.add(new Paragraph("\nResultado do Exame").setBold().setFontSize(14));

            // Corpo
            String tipo = (String) dadosLaudo.get("tipoExame");
            doc.add(new Paragraph("Tipo de Exame: " + tipo));
            
            // Formatação específica por tipo de exame
            formatarConteudoEspecifico(doc, exameTipo, dados);

            // Rodapé
            doc.add(new Paragraph("\n"));
            Paragraph rodape = new Paragraph("Médico responsável: Dr(a). " + dados.getOrDefault("medicoResponsavel", "—"))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);
            doc.add(rodape);
            
            Paragraph dataEmissao = new Paragraph("Data de Emissão: " + dataHoje.format(formatter))
                    .setTextAlignment(TextAlignment.CENTER);
            doc.add(dataEmissao);

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
    
    private void formatarConteudoEspecifico(Document doc, ExameProcedimento exame, Map<String, String> dados) {        
        if (exame instanceof ExameSangue) {
            formatarExameSangue(doc, dados);
        } else if (exame instanceof ExameColonoscopia) {
            formatarExameColonoscopia(doc, dados);
        } else if (exame instanceof ExameEndoscopiaDigestivaAlta) {
            formatarExameEndoscopiaDigestivaAlta(doc, dados);
        } else if (exame instanceof ExameRaioX) {
            formatarExameRaioX(doc, dados);
        } else if (exame instanceof ExameRessonanciaMagnetica) {
            formatarExameRessonanciaMagnetica(doc, dados);
        } else {
            doc.add(new Paragraph("Formato de exame não suportado."));
        }
    }
    
    private void formatarExameSangue(Document doc, Map<String, String> dados) {
        doc.add(new Paragraph("Bioquímica").setBold());
        Table tableBio = new Table(2);
        
        String glicose = dados.getOrDefault("glicose", "—") + " mg/dL";
        if (dados.containsKey("glicoseDiagnostico")) {
            glicose += " - " + dados.get("glicoseDiagnostico");
        }
        tableBio.addCell("Glicose");
        tableBio.addCell(glicose);
        
        String colesterol = dados.getOrDefault("colesterol", "—") + " mg/dL";
        if (dados.containsKey("colesterolDiagnostico")) {
            colesterol += " - " + dados.get("colesterolDiagnostico");
        }
        tableBio.addCell("Colesterol Total");
        tableBio.addCell(colesterol);
        
        String creatinina = dados.getOrDefault("creatinina", "—") + " mg/dL";
        if (dados.containsKey("creatininaDiagnostico")) {
            creatinina += " - " + dados.get("creatininaDiagnostico");
        }
        tableBio.addCell("Creatinina");
        tableBio.addCell(creatinina);
        
        doc.add(tableBio);
    }
    
    private void formatarExameColonoscopia(Document doc, Map<String, String> dados) {
        Table table = new Table(2);
        table.addCell("Laudo de Colonoscopia:");
        table.addCell(dados.getOrDefault("descricao", "—"));
        table.addCell("Endoscopista responsável:");
        table.addCell(dados.getOrDefault("endoscopista", "—"));
        table.addCell("Preparo Intestinal:");
        table.addCell(dados.getOrDefault("preparoIntestinal", "Não informado"));
        doc.add(table);
    }
    
    private void formatarExameEndoscopiaDigestivaAlta(Document doc, Map<String, String> dados) {
        Table table = new Table(2);
        table.addCell("Laudo de Endoscopia Digestiva Alta:");
        table.addCell(dados.getOrDefault("descricao", "—"));
        table.addCell("Endoscopista responsável:");
        table.addCell(dados.getOrDefault("endoscopista", "—"));
        table.addCell("Sedação:");
        table.addCell(dados.getOrDefault("sedacao", "Não informada"));
        doc.add(table);
    }
    
    private void formatarExameRaioX(Document doc, Map<String, String> dados) {
        Table table = new Table(2);
        table.addCell("Laudo radiológico:");
        table.addCell(dados.getOrDefault("descricao", "—"));
        table.addCell("Radiologista responsável:");
        table.addCell(dados.getOrDefault("radiologistaAss", "—"));
        table.addCell("Imagens anexadas:");
        table.addCell(dados.containsKey("imagens") ? "Sim" : "Não");
        doc.add(table);
    }
    
    private void formatarExameRessonanciaMagnetica(Document doc, Map<String, String> dados) {
        Table table = new Table(2);
        table.addCell("Laudo de Ressonância Magnética:");
        table.addCell(dados.getOrDefault("descricao", "—"));
        table.addCell("Protocolo:");
        table.addCell(dados.getOrDefault("protocolo", "—"));
        table.addCell("Contraste utilizado:");
        table.addCell(dados.getOrDefault("contraste", "Não"));
        if (dados.containsKey("dosagemContraste") && !dados.get("dosagemContraste").isEmpty()) {
            table.addCell("Dosagem do contraste:");
            table.addCell(dados.get("dosagemContraste"));
        }
        table.addCell("Radiologista responsável:");
        table.addCell(dados.getOrDefault("radiologistaAss", "—"));
        doc.add(table);
    }
}
