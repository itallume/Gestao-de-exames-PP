package org.example.entities.laudo;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.models.Paciente;
import org.example.entities.exame.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaudoTxt implements ILaudo {

    @Override
    public Object gerarDocumento(ExameProcedimento exameTipo) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> dadosLaudo = exameTipo.getDadosParaLaudo();
        Paciente paciente = (Paciente) dadosLaudo.get("paciente");
        @SuppressWarnings("unchecked")
        Map<String, String> dados = (Map<String, String>) dadosLaudo.get("dadosBasicos");
        LocalDate dataHoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Cabeçalho
        sb.append("===== Laboratório ST Diagnósticos =====\n");
        sb.append("Paciente: ").append(paciente.getNome()).append("\n");
        sb.append("Idade: ").append(paciente.getIdade()).append(" anos\n");
        sb.append("Sexo: ").append(paciente.getSexo()).append("\n");
        sb.append("Convênio: ").append(paciente.getConvenio()).append("\n");
        sb.append("Telefone: ").append(paciente.getTelefone()).append("\n");
        sb.append("Médico Solicitante: ").append(dados.getOrDefault("medicoSolicitante", "—")).append("\n");
        sb.append("Data do Exame: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("ID do exame: ").append(dadosLaudo.get("id")).append("\n");
        sb.append("==========================================\n\n");
        sb.append("=== Resultado do Exame ===\n");

        String tipo = (String) dadosLaudo.get("tipoExame");
        sb.append("Tipo de Exame: ").append(tipo).append("\n");
        sb.append("==========================================\n");
        
        // Formatação específica por tipo de exame
        sb.append(formatarConteudoEspecifico(exameTipo, dados));
        
        sb.append("==========================================\n");
        sb.append("Médico Responsável: Dr(a). ").append(dados.getOrDefault("medicoResponsavel", "—")).append("\n");
        sb.append("Data de Emissão: ").append(dataHoje.format(formatter)).append("\n");
        sb.append("==========================================\n");

        return sb.toString();
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
        
        return "Formato de exame não suportado.\n";
    }
    
    private String formatarExameSangue(Map<String, String> dados) {
        StringBuilder sb = new StringBuilder();
        // Apenas os campos validados: colesterol, creatinina, glicose
        sb.append("=== Bioquímica ===\n");
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
        
        return sb.toString();
    }
    
    private String formatarExameColonoscopia(Map<String, String> dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("Laudo de Colonoscopia: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Endoscopista responsável: ").append(dados.getOrDefault("endoscopista", "—")).append("\n");
        sb.append("Preparo Intestinal: ").append(dados.getOrDefault("preparoIntestinal", "Não informado")).append("\n");
        return sb.toString();
    }
    
    private String formatarExameEndoscopiaDigestivaAlta(Map<String, String> dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("Laudo de Endoscopia Digestiva Alta: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Endoscopista responsável: ").append(dados.getOrDefault("endoscopista", "—")).append("\n");
        sb.append("Sedação: ").append(dados.getOrDefault("sedacao", "Não informada")).append("\n");
        return sb.toString();
    }
    
    private String formatarExameRaioX(Map<String, String> dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("Laudo radiológico: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Radiologista responsável: ").append(dados.getOrDefault("radiologistaAss", "—")).append("\n");
        sb.append("Imagens anexadas: ").append(dados.containsKey("imagens") ? "Sim" : "Não").append("\n");
        return sb.toString();
    }
    
    private String formatarExameRessonanciaMagnetica(Map<String, String> dados) {
        StringBuilder sb = new StringBuilder();
        sb.append("Laudo de Ressonância Magnética: ").append(dados.getOrDefault("descricao", "—")).append("\n");
        sb.append("Protocolo: ").append(dados.getOrDefault("protocolo", "—")).append("\n");
        sb.append("Contraste utilizado: ").append(dados.getOrDefault("contraste", "Não")).append("\n");
        if (dados.containsKey("dosagemContraste") && !dados.get("dosagemContraste").isEmpty()) {
            sb.append("Dosagem do contraste: ").append(dados.get("dosagemContraste")).append("\n");
        }
        sb.append("Radiologista responsável: ").append(dados.getOrDefault("radiologistaAss", "—")).append("\n");
        return sb.toString();
    }
}
