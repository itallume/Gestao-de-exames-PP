package org.example.entities.laudo;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;
import org.example.entities.exame.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoHTML implements ILaudo {

    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        Map<String, Object> dadosLaudo = exameTipo.getDadosParaLaudo();
        Paciente paciente = (Paciente) dadosLaudo.get("paciente");
        @SuppressWarnings("unchecked")
        Map<String, String> dados = (Map<String, String>) dadosLaudo.get("dadosBasicos");
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='pt-BR'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Laudo do Exame</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 20px; }");
        html.append("header, footer { text-align: center; margin-bottom: 20px; }");
        html.append("h1 { color: #1a73e8; }");
        html.append("table { width: 100%; border-collapse: collapse; margin-top: 10px; }");
        html.append("th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }");
        html.append("th { background-color: #f2f2f2; }");
        html.append("</style>");
        html.append("</head>");
        html.append("<body>");

        // Cabeçalho
        html.append("<header>");
        html.append("<h1>Laboratório ST Diagnósticos</h1>");
        html.append("<p><strong>Paciente:</strong> ").append(paciente.getNome()).append("</p>");
        html.append("<p><strong>Idade:</strong> ").append(paciente.getIdade()).append(" anos</p>");
        html.append("<p><strong>Sexo:</strong> ").append(paciente.getSexo()).append("</p>");
        html.append("<p><strong>Convênio:</strong> ").append(paciente.getConvenio()).append("</p>");
        html.append("<p><strong>Telefone:</strong> ").append(paciente.getTelefone()).append("</p>");
        html.append("<p><strong>Médico Solicitante:</strong> ").append(dados.getOrDefault("medicoSolicitante", "—"))
                .append("</p>");
        html.append("<p><strong>Data:</strong> ").append(dataHoje.format(formatter)).append("</p>");
        html.append("<p><strong>ID do exame:</strong> ").append(dadosLaudo.get("id")).append("</p>");
        html.append("</header>");

        // Corpo do exame
        html.append("<section>");
        html.append("<h2>Resultado do Exame</h2>");

        String tipo = (String) dadosLaudo.get("tipoExame");
        html.append("<p><strong>Tipo de exame:</strong> ").append(tipo).append("</p>");

        // Formatação específica por tipo de exame
        html.append(formatarConteudoEspecifico(exameTipo, dados));

        html.append("</section>");

        // Rodapé
        html.append("<footer>");
        html.append("<p><strong>Médico responsável:</strong> Dr(a). ")
                .append(dados.getOrDefault("medicoResponsavel", "—")).append("</p>");
        html.append("<p><strong>Data de Emissão:</strong> ").append(dataHoje.format(formatter)).append("</p>");
        html.append("</footer>");

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    private String formatarConteudoEspecifico(ExameProcedimento exame, Map<String, String> dados) {
        if (exame instanceof ExameSangue) {
            return formatarExameSangue(dados);
        } else if (exame instanceof ExameColonoscopia) {
            return formatarExameColonoscopia(dados);
        } else if (exame instanceof ExameEndoscopiaDigestivaAlta) {
            return formatarExameEndoscopiaDigestivaAlta(dados);
        } else if (exame instanceof ExameRaioX) {
            return formatarExameRaioX(dados);
        } else if (exame instanceof ExameRessonanciaMagnetica) {
            return formatarExameRessonanciaMagnetica(dados);
        }

        return "<p>Formato de exame não suportado.</p>";
    }

    private String formatarExameSangue(Map<String, String> dados) {
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><th colspan='2'>Bioquímica</th></tr>");
        html.append("<tr><td>Glicose</td><td>").append(dados.getOrDefault("glicose", "—")).append(" mg/dL");
        if (dados.containsKey("glicoseDiagnostico")) {
            html.append(" - <em>").append(dados.get("glicoseDiagnostico")).append("</em>");
        }
        html.append("</td></tr>");

        html.append("<tr><td>Colesterol Total</td><td>").append(dados.getOrDefault("colesterol", "—")).append(" mg/dL");
        if (dados.containsKey("colesterolDiagnostico")) {
            html.append(" - <em>").append(dados.get("colesterolDiagnostico")).append("</em>");
        }
        html.append("</td></tr>");

        html.append("<tr><td>Creatinina</td><td>").append(dados.getOrDefault("creatinina", "—")).append(" mg/dL");
        if (dados.containsKey("creatininaDiagnostico")) {
            html.append(" - <em>").append(dados.get("creatininaDiagnostico")).append("</em>");
        }
        html.append("</td></tr>");

        html.append("</table>");
        return html.toString();
    }

    private String formatarExameColonoscopia(Map<String, String> dados) {
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><td><strong>Laudo de Colonoscopia:</strong></td><td>")
                .append(dados.getOrDefault("descricao", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Endoscopista responsável:</strong></td><td>")
                .append(dados.getOrDefault("endoscopista", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Preparo Intestinal:</strong></td><td>")
                .append(dados.getOrDefault("preparoIntestinal", "Não informado")).append("</td></tr>");
        html.append("</table>");
        return html.toString();
    }

    private String formatarExameEndoscopiaDigestivaAlta(Map<String, String> dados) {
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><td><strong>Laudo de Endoscopia Digestiva Alta:</strong></td><td>")
                .append(dados.getOrDefault("descricao", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Endoscopista responsável:</strong></td><td>")
                .append(dados.getOrDefault("endoscopista", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Sedação:</strong></td><td>").append(dados.getOrDefault("sedacao", "Não informada"))
                .append("</td></tr>");
        html.append("</table>");
        return html.toString();
    }

    private String formatarExameRaioX(Map<String, String> dados) {
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><td><strong>Laudo radiológico:</strong></td><td>").append(dados.getOrDefault("descricao", "—"))
                .append("</td></tr>");
        html.append("<tr><td><strong>Radiologista responsável:</strong></td><td>")
                .append(dados.getOrDefault("radiologistaAss", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Imagens anexadas:</strong></td><td>")
                .append(dados.containsKey("imagens") ? "Sim" : "Não").append("</td></tr>");
        html.append("</table>");
        return html.toString();
    }

    private String formatarExameRessonanciaMagnetica(Map<String, String> dados) {
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr><td><strong>Laudo de Ressonância Magnética:</strong></td><td>")
                .append(dados.getOrDefault("descricao", "—")).append("</td></tr>");
        html.append("<tr><td><strong>Protocolo:</strong></td><td>").append(dados.getOrDefault("protocolo", "—"))
                .append("</td></tr>");
        html.append("<tr><td><strong>Contraste utilizado:</strong></td><td>")
                .append(dados.getOrDefault("contraste", "Não")).append("</td></tr>");
        if (dados.containsKey("dosagemContraste") && !dados.get("dosagemContraste").isEmpty()) {
            html.append("<tr><td><strong>Dosagem do contraste:</strong></td><td>").append(dados.get("dosagemContraste"))
                    .append("</td></tr>");
        }
        html.append("<tr><td><strong>Radiologista responsável:</strong></td><td>")
                .append(dados.getOrDefault("radiologistaAss", "—")).append("</td></tr>");
        html.append("</table>");
        return html.toString();
    }
}
