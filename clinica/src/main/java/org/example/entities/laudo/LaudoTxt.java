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
        sb.append("Idade: ").append(paciente.getIdade()).append(" anos\n");
        sb.append("Sexo: ").append(paciente.getSexo()).append("\n");
        sb.append("Convênio: ").append(paciente.getConvenio()).append("\n");
        sb.append("Telefone: ").append(paciente.getTelefone()).append("\n");
        sb.append("Médico Solicitante: ").append(dados.getOrDefault("medicoSolicitante", "—")).append("\n");
        sb.append("Data do Exame: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("ID do exame: ").append(exameTipo.getId()).append("\n");
        sb.append("==========================================\n\n");
        sb.append("=== Resultado do Exame ===\n");

        String tipo = (String) dados.getOrDefault("tipoExame", "Não especificado");
        sb.append("Tipo de Exame: ").append(tipo).append("\n");
        sb.append("==========================================\n");
        sb = exameTipo.montarTxt(sb, dados);
        sb.append("==========================================\n");
        sb.append("Médico Responsável: Dr(a). ").append(dados.getOrDefault("medicoResponsavel", "—")).append("\n");
        sb.append("Data de Emissão: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("==========================================\n");

        return sb.toString();
    }
}
