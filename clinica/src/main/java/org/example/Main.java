package org.example;

import org.example.Enum.Campanha;
import org.example.Enum.Prioridade;
import org.example.entities.LaboratorioFachada;
import org.example.entities.exame.ExameColonoscopia;
import org.example.entities.exame.ExameEndoscopiaDigestivaAlta;
import org.example.entities.exame.ExameSangue;
import org.example.entities.laudo.LaudoTxt;
import org.example.entities.models.ExameOrdem;
import org.example.entities.models.Medico;
import org.example.entities.models.Paciente;
import org.example.entities.notificador.NotificardorEmail;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Cadastro de paciente e médicos
        Paciente paciente = new Paciente("Maria", 65, "999999999", "maria@email.com", "Unimed", "F");
        Medico medicoSolicitante = new Medico("Dr. João", "12345", "Clínico Geral");
        Medico medicoResponsavel = new Medico("Dra. Ana", "54321", "Endoscopia");

        // Instanciar fachada e exames
        LaboratorioFachada lab = new LaboratorioFachada();
        ExameColonoscopia exame1 = new ExameColonoscopia();
        ExameEndoscopiaDigestivaAlta exame2 = new ExameEndoscopiaDigestivaAlta();
        ExameSangue exame3 = new ExameSangue();

        // Requisitar exames
        ExameOrdem ordem1 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame1,
                Prioridade.URGENTE);
        ExameOrdem ordem2 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame2,
                Prioridade.POUCO_URGENTE);
        ExameOrdem ordem3 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame3,
                Prioridade.ROTINA);

        // Corrige: garante que o paciente está setado dentro do exame
        ordem1.getExameTipo().setPaciente(paciente);
        ordem2.getExameTipo().setPaciente(paciente);
        ordem3.getExameTipo().setPaciente(paciente);

        List<ExameOrdem> exames = Arrays.asList(ordem1, ordem2, ordem3);

        // Calcular preço com descontos (idoso, convênio, campanha)
        List<Campanha> campanhas = Arrays.asList(Campanha.NOVEMBRO_AZUL);
        double precoTotal = lab.calcularPreco(exames, paciente, campanhas);
        System.out.println("Preço total com descontos: R$ " + precoTotal);

        // Pagar exames
        lab.pagarExames(exames, paciente, campanhas);

        // Entrar na fila de prioridade
        lab.entrarNaFilaDeEspera(ordem1);
        lab.entrarNaFilaDeEspera(ordem2);
        lab.entrarNaFilaDeEspera(ordem3);

        // Chamar e realizar exames
        for (int i = 0; i < exames.size(); i++) {
            ExameOrdem proximo = lab.chamarProximoDaFila();
            lab.realizarExame(proximo);

            // Preencher dados do exame
            Map<String, String> dados = new HashMap<>();
            dados.put("descricao", "Exame realizado com sucesso.");
            dados.put("endoscopista", medicoResponsavel.getNome());
            dados.put("preparoIntestinal", "Adequado");
            dados.put("sedacao", "Sim");
            dados.put("resultados", "Normal");
            dados.put("valoresReferencia", "Normal");
            dados.put("responsavelTecnico", medicoResponsavel.getNome());
            dados.put("tipoExame", proximo.getExameTipo().getClass().getSimpleName());
            dados.put("medicoSolicitante", medicoSolicitante.getNome());
            dados.put("medicoResponsavel", medicoResponsavel.getNome());

            lab.emitirLaudo(proximo, dados);

            // Gerar laudo em TXT
            LaudoTxt laudoTxt = new LaudoTxt();

            System.out.println(laudoTxt.gerarDocumento(proximo.getExameTipo()));

            NotificardorEmail notificador = new NotificardorEmail();
            notificador.notificar(paciente, laudoTxt);
        }
    }
}