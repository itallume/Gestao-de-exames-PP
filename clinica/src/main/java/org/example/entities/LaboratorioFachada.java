package org.example.entities;

import org.example.Enum.Prioridade;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.desconto.DescontoFachada;
import org.example.entities.filaPrioridade.FilaPrioridadeExame;
import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.models.Exame;
import org.example.entities.models.Medico;
import org.example.entities.models.Paciente;
import org.example.entities.models.Pagamento;

import java.util.List;
import java.util.Map;

public class LaboratorioFachada {
    private FilaPrioridadeExame fila;
    private GeradorId geradorId;
    //TODO jogar pro construtor
    private final ExameVisitor<Double> precoVisitor = new PrecoVisitor();

    public Exame requisitarExame(Paciente paciente, Medico medicoSolicitante, Medico medicoSResponsavel, ExameTipo exameTipo, Prioridade prioridade){
        return new Exame(geradorId.gerarId(), paciente, medicoSolicitante, medicoSResponsavel, exameTipo, prioridade);
    }

    public double calcularPreco(List<Exame> exames, Paciente paciente){
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente);
        return pagamento.calcularPreco(precoVisitor);
    }

    public void pagarExames(List<Exame> exames, Paciente paciente){
        //TODO dar uma olhada aqui
        Desconto pagamento = DescontoFachada.verificarDescontosPossiveis(exames, paciente);
        pagamento.pagar();
    }

    public void entrarNaFilaDeEspera(Exame exame){
        fila.adicionarExame(exame);
    }

    public Exame chamarProximoDaFila() throws Exception {
        return fila.chamarProximo();
    }

    public void realizarExame(Exame exame) throws Exception {
        if (!exame.isPago()){
            throw new Exception("Não é possével realizar o exame de id "+ exame.getId() +", pois não está pago.");
        }
        exame.realizarExame();
    }

    public void preencherDadosExame(Exame exame, Map<String, String> dados){
        exame.preencherDados(dados);
    }



}
