package org.example.entities.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.Prioridade;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {
    private int id;
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoResponsavel;
    private ExameTipo exameTipo;
    private boolean isPago;
    private Date data;
    private Date dataRealizacao;
    private Prioridade prioridade;

    public Double calcularPreco(ExameVisitor<Double> visitor){
        return exameTipo.aceitar(visitor);
    }

}



