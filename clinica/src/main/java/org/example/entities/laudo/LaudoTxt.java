package org.example.entities.laudo;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoTxt implements ILaudo{



    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        StringBuilder sb = new StringBuilder();
        Paciente paciente = exameTipo.getPaciente();
        Object dados = exameTipo.getDados();
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("===== Laboratório ST Diagnósticos =====\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Convênio: ").append(paciente.getConvenio()).append("\n");

        if (dados instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) dados;
            sb.append("Médico Solicitante: ").append(map.getOrDefault("medicoSolicitante", "—")).append("\n");
        } else {
            sb.append("Médico Solicitante: —\n");
        }

        sb.append("Data: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("ID do exame: ").append(exameTipo.getId()).append("\n\n");
        sb.append("=== Resultado do Exame ===\n");

        if (dados instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) dados;
            String tipo = (String) map.getOrDefault("tipoExame", "Não especificado");
            sb.append("Tipo de Exame: ").append(tipo).append("\n");

            switch (tipo.toLowerCase()) {
                case "sanguineo" -> {
                    Map<String, String> resultados = (Map<String, String>) map.get("resultados");
                    if (resultados != null) {
                        for (Map.Entry<String, String> entry : resultados.entrySet()) {
                            sb.append("- ").append(entry.getKey())
                                    .append(": ").append(entry.getValue()).append("\n");
                        }
                        sb.append("Valores de Referência: ")
                                .append(map.getOrDefault("valoresReferencia", "—")).append("\n");
                    }
                    sb.append("Responsável técnico: ")
                            .append(map.getOrDefault("responsavelTecnico", "—")).append("\n");
                }

                case "raiox" -> {
                    sb.append("Laudo radiológico: ").append(map.getOrDefault("descricao", "—")).append("\n");
                    sb.append("Radiologista responsável: ").append(map.getOrDefault("radiologista", "—")).append("\n");
                }

                case "ressonancia" -> {
                    sb.append("Laudo de Ressonância: ").append(map.getOrDefault("descricao", "—")).append("\n");
                    sb.append("Protocolo: ").append(map.getOrDefault("protocolo", "—")).append("\n");
                    sb.append("Contraste utilizado: ").append(map.getOrDefault("contraste", "Não")).append("\n");
                    sb.append("Radiologista responsável: ").append(map.getOrDefault("radiologista", "—")).append("\n");
                }

                default -> sb.append("Dados do exame não especificados.\n");
            }
        } else {
            sb.append("Dados do exame indisponíveis.\n");
        }

        sb.append("\n=== Médico Responsável ===\n");

        if (exameTipo.getLaudo() != null && exameTipo.getDados() instanceof Map) {
            Map<String, String> map = (Map<String, String>) exameTipo.getDados();
            sb.append("Dr(a). ").append(map.getOrDefault("medicoResponsavel", "—")).append("\n");
        } else {
            sb.append("—\n");
        }

        return sb.toString();
    }
}
