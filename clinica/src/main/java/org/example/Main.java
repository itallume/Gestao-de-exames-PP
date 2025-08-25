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

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Cadastro de paciente e médicos
        System.out.println("----------------- Cadastrando pacientes -----------------");
        Paciente paciente = new Paciente("Maria", 65, "999999999", "laurostephan@gmail.com", "Unimed", "F");
        Medico medicoSolicitante = new Medico("João", "12345", "Clínico Geral");
        Medico medicoResponsavel = new Medico("Ana", "54321", "Endoscopia");

        // Instanciar fachada e exames
        System.out.println("----------------- Instanciando fachadas -----------------");
        LaboratorioFachada lab = new LaboratorioFachada();
        ExameColonoscopia exame1 = new ExameColonoscopia();
        ExameEndoscopiaDigestivaAlta exame2 = new ExameEndoscopiaDigestivaAlta();
        ExameSangue exame3 = new ExameSangue();
        org.example.entities.exame.ExameRaioX exame4 = new org.example.entities.exame.ExameRaioX();
        org.example.entities.exame.ExameRessonanciaMagnetica exame5 = new org.example.entities.exame.ExameRessonanciaMagnetica();

        // Requisitar exames
        System.out.println("----------------- Requisitando exames -----------------");
        ExameOrdem ordem1 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame1,
                Prioridade.URGENTE);
        ExameOrdem ordem2 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame2,
                Prioridade.POUCO_URGENTE);
        ExameOrdem ordem3 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame3,
                Prioridade.ROTINA);
        ExameOrdem ordem4 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame4,
                Prioridade.URGENTE);
        ExameOrdem ordem5 = lab.requisitarExame(paciente, medicoSolicitante, medicoResponsavel, exame5,
                Prioridade.POUCO_URGENTE);

        List<ExameOrdem> exames = Arrays.asList(ordem1, ordem2, ordem3, ordem4, ordem5);

        // Calcular preço com descontos (idoso, convênio, campanha)
        System.out.println("----------------- Calculando preço do exames -----------------");
        List<Campanha> campanhas = Arrays.asList(Campanha.NOVEMBRO_AZUL);
        double precoTotal = lab.calcularPreco(exames, paciente, campanhas);
        System.out.println("Preço total com descontos: R$ " + precoTotal);

        // Pagar exames
        System.out.println("----------------- Pagando exames -----------------");
        lab.pagarExames(exames, paciente, campanhas);

        // Entrar na fila de prioridade
        lab.entrarNaFilaDeEspera(ordem1);
        lab.entrarNaFilaDeEspera(ordem2);
        lab.entrarNaFilaDeEspera(ordem3);
        lab.entrarNaFilaDeEspera(ordem4);
        lab.entrarNaFilaDeEspera(ordem5);

        // Chamar e realizar exames
        for (int i = 0; i < exames.size(); i++) {
            System.out.println("----------------- Iniciando exame -----------------" + " " + i);
            ExameOrdem proximo = lab.chamarProximoDaFila();
            lab.realizarExame(proximo);

            // Preencher dados do exame baseado no tipo
            Map<String, String> dados = new HashMap<>();
            
            // Dados básicos para todos os exames
            dados.put("tipoExame", proximo.getExameTipo().getClass().getSimpleName());
            dados.put("medicoSolicitante", medicoSolicitante.getNome());
            dados.put("medicoResponsavel", medicoResponsavel.getNome());
            
            // Dados específicos por tipo de exame
            if (proximo.getExameTipo() instanceof ExameColonoscopia) {
                // Dados obrigatórios para Colonoscopia
                dados.put("descricao", "Colonoscopia realizada com sucesso. Mucosa intestinal preservada, sem sinais de lesões ou alterações patológicas.");
                dados.put("endoscopista", medicoResponsavel.getNome());
                dados.put("preparoIntestinal", "Adequado - preparo com polietilenoglicol");
                dados.put("sedacao", "Realizada sedação consciente com midazolam");
                dados.put("achados", "Mucosa normal, sem pólipos ou lesões");
                
            } else if (proximo.getExameTipo() instanceof ExameEndoscopiaDigestivaAlta) {
                // Dados obrigatórios para Endoscopia Digestiva Alta
                dados.put("descricao", "Endoscopia digestiva alta sem alterações. Esôfago, estômago e duodeno com mucosa normal.");
                dados.put("endoscopista", medicoResponsavel.getNome());
                dados.put("sedacao", "Sedação leve com propofol");
                dados.put("achados", "Mucosa esofágica, gástrica e duodenal normais");
                dados.put("biopsias", "Não foram realizadas biópsias");
                
            } else if (proximo.getExameTipo() instanceof ExameSangue) {
                // Dados para validadores laboratoriais (Colesterol, Creatinina, Glicose)
                dados.put("descricao", "Exame de sangue - hemograma completo e bioquímica");
                dados.put("colesterol", "180.5");  // Valor desejável
                dados.put("creatinina", "0.9");    // Valor normal
                dados.put("glicose", "85.0");      // Valor normal
                dados.put("hemoglobina", "14.2");
                dados.put("hematocritos", "42.1");
                dados.put("leucocitos", "7200");
                dados.put("plaquetas", "280000");
                dados.put("responsavelTecnico", medicoResponsavel.getNome());
                dados.put("laboratorio", "Laboratório ST Diagnósticos");
                
            } else if (proximo.getExameTipo() instanceof org.example.entities.exame.ExameRaioX) {
                // Dados obrigatórios para Raio-X
                dados.put("descricao", "Radiografia de tórax PA e perfil. Campos pulmonares limpos, coração de tamanho normal.");
                dados.put("radiologistaAss", medicoResponsavel.getNome());
                dados.put("imagens", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAAA..."); // Base64 simulado
                dados.put("regiao", "Tórax");
                dados.put("posicoes", "PA e Perfil");
                dados.put("tecnica", "Convencional");
                
            } else if (proximo.getExameTipo() instanceof org.example.entities.exame.ExameRessonanciaMagnetica) {
                // Dados obrigatórios para Ressonância Magnética
                dados.put("descricao", "Ressonância magnética do crânio. Estruturas encefálicas preservadas, sem sinais de lesões.");
                dados.put("radiologistaAss", medicoResponsavel.getNome());
                dados.put("protocolo", "T1, T2, FLAIR e DWI");
                dados.put("contraste", "Gadolínio");
                dados.put("dosagemContraste", "0.1 mmol/kg");
                dados.put("campo", "1.5 Tesla");
                dados.put("regiao", "Crânio");
                dados.put("sequencias", "T1, T2, FLAIR, DWI");
            }

            System.out.println("----------------- Dados do exame preenchidos -----------------" + " " + i);
            System.out.println(dados.toString());

            // Criar o laudo antes de emitir
            LaudoTxt laudoTxt = new LaudoTxt();
            
            // Emitir laudo com notificação automática (inclui anexo)
            lab.emitirLaudoComNotificacao(proximo, dados, laudoTxt);
            System.out.println("----------------- Laudo emitido e enviado por email com anexo -----------------" + " " + i);
        }
    }
}