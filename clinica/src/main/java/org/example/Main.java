    package org.example;

    import org.example.Enum.Campanha;
    import org.example.Enum.Prioridade;
    import org.example.entities.Funcionario;
    import org.example.entities.LaboratorioFachada;
    import org.example.entities.abstracts.ExameProcedimento;
    import org.example.entities.carregador.MedicoCarregador;
    import org.example.entities.carregador.PacienteCarregador;
    import org.example.entities.exame.*;
    import org.example.entities.laudo.LaudoPdf;
    import org.example.entities.models.ExameOrdem;
    import org.example.entities.models.Medico;
    import org.example.entities.models.Paciente;

    import java.util.*;

    public class Main {
        public static void main(String[] args) throws Exception {

            // Funcionário "Logado" para registrar os logs
            Funcionario.setNome("Alex");

            System.out.println("----------------- Cadastrando pacientes -----------------");

            // Carregar médicos
            MedicoCarregador medicoCarregador = new MedicoCarregador();
            List<String> dadosMedicos = medicoCarregador.carregarDados("csv/medicos.csv");
            List<Medico> medicos = medicoCarregador.parseDados(dadosMedicos);

            Medico medicoSolicitante = medicos.get(0);
            Medico medicoResponsavel = medicos.get(1);
            Medico medicoSolicitante2 = medicos.get(2);
            Medico medicoResponsavel2 = medicos.get(3);

            PacienteCarregador pacienteCarregador = new PacienteCarregador();
            List<String> dadosPacientes = pacienteCarregador.carregarDados("csv/pacientes.csv");
            List<Paciente> pacientes = pacienteCarregador.parseDados(dadosPacientes);

            Paciente paciente1 = pacientes.get(0);
            Paciente paciente2 = pacientes.get(1);
            Paciente paciente3 = pacientes.get(2);
            Paciente paciente4 = pacientes.get(3);
            Paciente paciente5 = pacientes.get(4);

            System.out.println("----------------- Instanciando fachadas -----------------");
            LaboratorioFachada lab = new LaboratorioFachada();
            ExameProcedimento exame1 = new ExameColonoscopia();
            ExameProcedimento exame2 = new ExameEndoscopiaDigestivaAlta();
            ExameProcedimento exame3 = new ExameSangue();
            ExameProcedimento exame4 = new ExameRaioX();
            ExameProcedimento exame5 = new ExameRessonanciaMagnetica();
            ExameProcedimento exame6 = new ExameRaioX();

            System.out.println("----------------- Requisitando exames -----------------");
            ExameOrdem ordem1 = lab.requisitarExame(paciente1, medicoSolicitante, medicoResponsavel, exame1,
                    Prioridade.URGENTE);
            ExameOrdem ordem2 = lab.requisitarExame(paciente2, medicoSolicitante2, medicoResponsavel2, exame2,
                    Prioridade.POUCO_URGENTE);
            ExameOrdem ordem3 = lab.requisitarExame(paciente3, medicoSolicitante2, medicoResponsavel, exame3,
                    Prioridade.ROTINA);
            ExameOrdem ordem4 = lab.requisitarExame(paciente4, medicoSolicitante, medicoResponsavel2, exame4,
                    Prioridade.URGENTE);
            ExameOrdem ordem5 = lab.requisitarExame(paciente5, medicoSolicitante2, medicoResponsavel, exame5,
                    Prioridade.POUCO_URGENTE);
            ExameOrdem ordem6 = lab.requisitarExame(paciente5, medicoSolicitante, medicoResponsavel2, exame6,
                    Prioridade.POUCO_URGENTE);


            List<ExameOrdem> examesItallo = Collections.singletonList(ordem1);
            List<ExameOrdem> examesPaulo = Collections.singletonList(ordem2);
            List<ExameOrdem> examesLauro = Collections.singletonList(ordem3);
            List<ExameOrdem> examesFernando = Collections.singletonList(ordem4);
            List<ExameOrdem> examesMarcos = Arrays.asList(ordem5, ordem6);


            System.out.println("----------------- Calculando preço do exames -----------------");
            List<Campanha> campanhasAtivas = List.of(Campanha.NOVEMBRO_AZUL);

            double precoTotalexamesItallo = lab.calcularPreco(examesItallo, paciente1, campanhasAtivas);
            System.out.println("Preço total com descontos exames Itallo: R$ " + precoTotalexamesItallo);

            double precoTotalexamesPaulo = lab.calcularPreco(examesPaulo, paciente2, campanhasAtivas);
            System.out.println("Preço total com descontos exames Paulo: R$ " + precoTotalexamesPaulo);

            double precoTotalexamesLauro = lab.calcularPreco(examesLauro, paciente3, campanhasAtivas);
            System.out.println("Preço total com descontos exames Lauro: R$ " + precoTotalexamesLauro);

            double precoTotalexamesFernando = lab.calcularPreco(examesFernando, paciente4, campanhasAtivas);
            System.out.println("Preço total com descontos exames Fernando: R$ " + precoTotalexamesFernando);

            double precoTotalexamesMarcos = lab.calcularPreco(examesMarcos, paciente5, campanhasAtivas);
            System.out.println("Preço total com descontos exames Marcos: R$ " + precoTotalexamesMarcos);

            System.out.println("----------------- Pagando exames -----------------");
            lab.pagarExames(examesItallo, paciente1, campanhasAtivas);
            lab.pagarExames(examesPaulo, paciente2, campanhasAtivas);
            lab.pagarExames(examesLauro, paciente3, campanhasAtivas);
            lab.pagarExames(examesFernando, paciente4, campanhasAtivas);
            lab.pagarExames(examesMarcos, paciente5, campanhasAtivas);

            // Entrar na fila de prioridade
            lab.entrarNaFilaDeEspera(ordem1);
            lab.entrarNaFilaDeEspera(ordem2);
            lab.entrarNaFilaDeEspera(ordem3);
            lab.entrarNaFilaDeEspera(ordem4);
            lab.entrarNaFilaDeEspera(ordem5);
            lab.entrarNaFilaDeEspera(ordem6);


            // Chamar e realizar exames
            while(lab.hasProximo()) {
                System.out.println("----------------- Iniciando exame -----------------");
                ExameOrdem proximo = lab.chamarProximoDaFila();
                lab.realizarExame(proximo);

                Map<String, String> dados = new HashMap<>();

                // Dados básicos para todos os exames
                dados.put("tipoExame", proximo.getExameTipo().getClass().getSimpleName());
                dados.put("medicoSolicitante", medicoSolicitante.getNome());
                dados.put("medicoResponsavel", medicoResponsavel.getNome());

                if (proximo.getExameTipo() instanceof ExameColonoscopia) {
                    // Campos obrigatórios validados: descricao, endoscopista
                    dados.put("descricao", "Colonoscopia realizada com sucesso. Mucosa intestinal preservada, sem sinais de lesões ou alterações patológicas.");
                    dados.put("endoscopista", medicoResponsavel.getNome());
                    dados.put("preparoIntestinal", "Adequado - preparo com polietilenoglicol");

                } else if (proximo.getExameTipo() instanceof ExameEndoscopiaDigestivaAlta) {
                    // Campos obrigatórios validados: descricao, endoscopista
                    dados.put("descricao", "Endoscopia digestiva alta sem alterações. Esôfago, estômago e duodeno com mucosa normal.");
                    dados.put("endoscopista", medicoResponsavel.getNome());
                    dados.put("sedacao", "Sedação leve com propofol");

                } else if (proximo.getExameTipo() instanceof ExameSangue) {
                    // Campos validados pelos validadores laboratoriais: colesterol, creatinina, glicose
                    // Apenas estes 3 campos são obrigatórios e validados
                    dados.put("colesterol", "180.5");  // Valor desejável
                    dados.put("creatinina", "0.9");    // Valor normal
                    dados.put("glicose", "85.0");      // Valor normal

                } else if (proximo.getExameTipo() instanceof ExameRaioX) {
                    // Campos obrigatórios validados: descricao, radiologistaAss, imagens (Base64)
                    dados.put("descricao", "Radiografia de tórax PA e perfil. Campos pulmonares limpos, coração de tamanho normal.");
                    dados.put("radiologistaAss", medicoResponsavel.getNome());
                    dados.put("imagens", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAAA...");

                } else if (proximo.getExameTipo() instanceof ExameRessonanciaMagnetica) {
                    // Campos obrigatórios validados: descricao, radiologistaAss, protocolo
                    // Campos condicionais validados: contraste, dosagemContraste (obrigatória se contraste usado)
                    dados.put("descricao", "Ressonância magnética do crânio. Estruturas encefálicas preservadas, sem sinais de lesões.");
                    dados.put("radiologistaAss", medicoResponsavel.getNome());
                    dados.put("protocolo", "T1, T2, FLAIR e DWI");
                    dados.put("contraste", "Gadolínio");
                    dados.put("dosagemContraste", "0.1 mmol/kg");
                }

                System.out.println("----------------- Dados do exame preenchidos -----------------");
                System.out.println(dados);

                LaudoPdf laudoPdf = new LaudoPdf();

                // Emitir laudo com notificação automática
                lab.emitirLaudoComNotificacao(proximo, dados, laudoPdf);
                System.out.println("----------------- Laudo emitido e enviado por email com anexo -----------------");
            }
        }
    }