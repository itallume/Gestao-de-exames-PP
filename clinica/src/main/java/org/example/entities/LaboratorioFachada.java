package org.example.entities;

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
        return new ExameOrdem(id, paciente, medicoSolicitante, medicoSResponsavel, exameTipo,
                prioridade);
    }

    public double calcularPreco(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas) {
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        return pagamento.calcularPreco(precoVisitor);
    }

    public void pagarExames(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas) {
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        pagamento.pagar();
    }

    public void entrarNaFilaDeEspera(ExameOrdem exame) {
        fila.adicionarExame(exame);
    }

    public ExameOrdem chamarProximoDaFila() throws Exception {
        return fila.chamarProximo();
    }

    public boolean hasProximo(){
        return fila.hasProximo();
    }

    public void realizarExame(ExameOrdem exame) throws Exception {
        if (!exame.isPago()) {
            throw new Exception("Não é possével realizar o exame de id " + exame.getId() + ", pois não está pago.");
        }
        exame.realizarExame();
    }

    public void emitirLaudo(ExameOrdem exame, Map<String, String> dados) {
        exame.preencherDados(dados);

        IValidador validador = ValidadorFachada.getValidador(exame);
        try {
            validador.validar(exame.getExameTipo());
        } catch (Exception e) {
            throw new RuntimeException("Erro de validação: " + e.getMessage(), e);
        }
    }

    public void emitirLaudoComNotificacao(ExameOrdem exame, Map<String, String> dados, ILaudo tipoLaudo) throws Exception {
        emitirLaudo(exame, dados);

        try {
            NotificardorEmail notificador = new NotificardorEmail();
            notificador.notificarComAnexo(exame.getPaciente(), tipoLaudo, exame.getExameTipo());
        } catch (Exception e) {
            throw new Exception("Erro ao enviar notificação: " + e.getMessage(), e);
        }
    }

}
