package org.example.entities.laudo;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoTxt implements ILaudo {

    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        StringBuilder sb = new StringBuilder();
        Paciente paciente = exameTipo.getPaciente();
        Map<String, String> dados = exameTipo.getDados();
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        sb.append("===== Laboratório ST Diagnósticos =====\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Convênio: ").append(paciente.getConvenio()).append("\n");
        sb.append("Médico Solicitante: ").append(dados.getOrDefault("medicoSolicitante", "—")).append("\n");
        sb.append("Data: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("ID do exame: ").append(exameTipo.getId()).append("\n\n");
        sb.append("=== Resultado do Exame ===\n");

        String tipo = (String) dados.getOrDefault("tipoExame", "Não especificado");
        sb.append("Tipo de Exame: ").append(tipo).append("\n");
        sb = exameTipo.montarTxt(sb, dados);
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
