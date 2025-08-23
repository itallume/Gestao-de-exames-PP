package org.example.entities.models;

import lombok.Data;
import org.example.entities.abstracts.ExameTipo;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Date;

@Data
public class Exame {
    private Paciente paciente;
    private Medico medicoSolicitante;
    private Medico medicoResponsavel;
    private ExameTipo exameTipo;
    private boolean isPago;
    private Date data;
    private Date dataRealizacao;

    public Double calcularPreco(ExameVisitor<Double> visitor){
        return exameTipo.aceitar(visitor);
    }

}



