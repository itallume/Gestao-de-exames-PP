package org.example.entities;

import org.example.Enum.Campanha;
import org.example.Enum.Prioridade;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.desconto.DescontoFachada;
import org.example.entities.filaPrioridade.FilaPrioridadeExame;
import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.models.ExameOrdem;
import org.example.entities.models.Medico;
import org.example.entities.models.Paciente;

import java.util.List;
import java.util.Map;

public class LaboratorioFachada {
    private FilaPrioridadeExame fila;
    private GeradorId geradorId;
    //TODO jogar pro construtor
    private final ExameVisitor<Double> precoVisitor = new PrecoVisitor();

    public ExameOrdem requisitarExame(Paciente paciente, Medico medicoSolicitante, Medico medicoSResponsavel, ExameProcedimento exameTipo, Prioridade prioridade){
        return new ExameOrdem(geradorId.gerarId(), paciente, medicoSolicitante, medicoSResponsavel, exameTipo, prioridade);
    }

    public double calcularPreco(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas){
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        return pagamento.calcularPreco(precoVisitor);
    }

    public void pagarExames(List<ExameOrdem> exames, Paciente paciente, List<Campanha> campanhasAtivas){
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente, campanhasAtivas);
        pagamento.pagar();
    }

    public void entrarNaFilaDeEspera(ExameOrdem exame){
        fila.adicionarExame(exame);
    }

    public ExameOrdem chamarProximoDaFila() throws Exception {
        return fila.chamarProximo();
    }

    public void realizarExame(ExameOrdem exame) throws Exception {
        if (!exame.isPago()){
            throw new Exception("Não é possével realizar o exame de id "+ exame.getId() +", pois não está pago.");
        }
        exame.realizarExame();
    }

    public void emitirLaudo(ExameOrdem exame, Map<String, String> dados){
        exame.preencherDados(dados);
    }



}
