package org.example.entities;

import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.models.Exame;
import org.example.entities.models.Paciente;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Pagamento implements Desconto {
    private List<Exame> exames;
    //TODO desconto
    private Date dataPagamento;
    private Paciente paciente;

    public Pagamento(List<Exame> exames, Paciente paciente){
        this.exames = exames;
        this.paciente = paciente;
    }

    public Double calcularPreco(ExameVisitor<Double> visitor){
        double preco = 0;
        for (Exame e : exames){
            preco += e.calcularPreco(visitor);
        }
        return preco;
    }

    public void pagar(ExameVisitor<Double> visitor) throws Exception {
        for (Exame e : exames){
            e.setPago(true);
        }

        this.dataPagamento = new Date();
    }
}
