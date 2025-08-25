package org.example.entities;

import org.example.LoggerMain;
import org.example.Enum.Campanha;
import org.example.Enum.Prioridade;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.desconto.DescontoFachada;
import org.example.entities.filaPrioridade.FilaPrioridadeExame;
import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.ILaudo;
import org.example.entities.interfaces.IValidador;
import org.example.entities.models.ExameOrdem;
import org.example.entities.models.Medico;
import org.example.entities.models.Paciente;
import org.example.entities.notificador.NotificardorEmail;
import org.example.entities.validador.ValidadorFachada;

import java.util.List;
import java.util.Map;

public class LaboratorioFachada {
    private FilaPrioridadeExame fila;

    public LaboratorioFachada() {
        this.fila = new FilaPrioridadeExame();
    }

    private final ExameVisitor<Double> precoVisitor = new PrecoVisitor();

    public ExameOrdem requisitarExame(Paciente paciente, Medico medicoSolicitante, Medico medicoSResponsavel,
            ExameProcedimento exameTipo, Prioridade prioridade) {
        int id = GeradorId.getInstance().gerarId();
        LoggerMain.info("O paciente " + paciente.getNome() + " requisitou o exame " + exameTipo.toString());
        return new ExameOrdem(id, paciente, medicoSolicitante, medicoSResponsavel, exameTipo,
                prioridade);
    }

    public double calcularPreco(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas) {
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        LoggerMain.info("O paciente " + paciente.getNome() + " solicitou calculo de preço");
        return pagamento.calcularPreco(precoVisitor);
    }

    public void pagarExames(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas) {
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        LoggerMain.info("O paciente " + paciente.getNome() + " tentou pagar o exame");
        pagamento.pagar();
        LoggerMain.info("O paciente " + paciente.getNome() + " pagou o exame");
    }

    public void entrarNaFilaDeEspera(ExameOrdem exame) {
        fila.adicionarExame(exame);
        LoggerMain.info("O paciente " + exame.getPaciente().getNome() + " entrou na fila de espera");
    }

    public ExameOrdem chamarProximoDaFila() throws Exception {
        return fila.chamarProximo();
    }

    public boolean hasProximo(){
        return fila.hasProximo();
    }

    public void realizarExame(ExameOrdem exame) throws Exception {
        if (!exame.isPago()) {
            throw new Exception("Não é possível realizar o exame de id " + exame.getId() + ", pois não está pago.");
        }
        LoggerMain.info("O paciente " + exame.getPaciente().getNome() + " tentou realizar o exame");
        exame.realizarExame();
    }

    public void emitirLaudo(ExameOrdem exame, Map<String, String> dados) {
        exame.preencherDados(dados);

        IValidador validador = ValidadorFachada.getValidador(exame);
        try {
            validador.validar(exame.getExameTipo());
            LoggerMain.info("O laudo do exame do paciente " + exame.getPaciente().getNome() + " foi emitido");
        } catch (Exception e) {
            LoggerMain.erro("O laudo do exame do paciente " + exame.getPaciente().getNome() + " não pode ser emitido");
            throw new RuntimeException("Erro de validação: " + e.getMessage(), e);
        }
    }

    public void emitirLaudoComNotificacao(ExameOrdem exame, Map<String, String> dados, ILaudo tipoLaudo) throws Exception {
        emitirLaudo(exame, dados);

        try {
            NotificardorEmail notificador = new NotificardorEmail();
            notificador.notificarComAnexo(exame.getPaciente(), tipoLaudo, exame.getExameTipo());
            LoggerMain.info("O laudo do exame do paciente " + exame.getPaciente().getNome() + " foi emitido com notificação");
        } catch (Exception e) {
            LoggerMain.erro("O laudo do exame do paciente " + exame.getPaciente().getNome() + " não pode ser emitido com notificação");
            throw new Exception("Erro ao enviar notificação: " + e.getMessage(), e);
        }
    }

}
