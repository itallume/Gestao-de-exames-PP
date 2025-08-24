package org.example.entities.laudo;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoHTML implements ILaudo {

    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        Paciente paciente = exameTipo.getPaciente();
        Map<String, String> dados = exameTipo.getDados();
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
        html.append("<p>Paciente: ").append(paciente.getNome()).append("</p>");
        html.append("<p>Convênio: ").append(paciente.getConvenio()).append("</p>");
        html.append("<p>Médico Solicitante: ").append(
                exameTipo.getDados() instanceof Map ? ((Map<?, ?>) exameTipo.getDados()).get("medicoSolicitante") : "—")
                .append("</p>");
        html.append("<p>Data: ").append(dataHoje.format(formatter)).append("</p>");
        html.append("<p>ID do exame: ").append(exameTipo.getId()).append("</p>");
        html.append("</header>");

        // Corpo do exame
        html.append("<section>");
        html.append("<h2>Resultado do Exame</h2>");

        String tipo = (String) dados.getOrDefault("tipoExame", "Não especificado");

        html.append("<p>Tipo de exame: ").append(tipo).append("</p>");
        html = exameTipo.montarHtml(html, dados);


        html.append("</section>");

        // Rodapé
        html.append("<footer>");
        html.append("<p>Médico responsável: ");

        if (exameTipo.getLaudo() != null && exameTipo.getDados() instanceof Map) {
            Map<String, String> dadosMap = (Map<String, String>) exameTipo.getDados();
            html.append("Dr(a). ").append(dadosMap.getOrDefault("medicoResponsavel", "—"));
        } else {
            html.append("—");
        }

        html.append("</p>");
        html.append("</footer>");

        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
