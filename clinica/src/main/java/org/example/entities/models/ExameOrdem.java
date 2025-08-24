package org.example.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.Prioridade;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExameOrdem {
    private int id;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoResponsavel;
    private ExameProcedimento exameTipo;
    private boolean isPago;
    private Date dataRealizacao;
    private Prioridade prioridade;

    public ExameOrdem(int id, Paciente paciente, Medico medicoSolicitante, Medico medicoResponsavel, ExameProcedimento exameTipo,
            Prioridade prioridade) {
        this.id = id;
        this.paciente = paciente;
        this.medicoSolicitante = medicoSolicitante;
        this.medicoResponsavel = medicoResponsavel;
        this.exameTipo = exameTipo;
        this.prioridade = prioridade;
    }

    public Double calcularPreco(ExameVisitor<Double> visitor) {
        return exameTipo.aceitar(visitor);
    }

    public void realizarExame() {
        exameTipo.realizarExame();
    }

    public void preencherDados(Map<String, String> dados) {
        exameTipo.preencherDados(dados);
    }

}
