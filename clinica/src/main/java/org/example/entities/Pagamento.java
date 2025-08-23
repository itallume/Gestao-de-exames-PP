package org.example.entities;

import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.models.Exame;
import org.example.entities.models.Paciente;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class Pagamento {
    private List<Exame> exames;
    //TODO desconto
    private Date dataPagamento;
    private Paciente paciente;

    public Pagamento(List<Exame> exames, Paciente paciente){
        this.exames = exames;
        this.paciente = paciente;
    }

    public double calcularPreco(ExameVisitor<Double> visitor){
        //TODO Desconto
        double preco = 0;
        for (Exame e : exames){
            preco += e.calcularPreco(visitor);
        }
        return preco;
    }

    //receber descxonto
    public void pagar(double valorRecebido, ExameVisitor<Double> visitor) throws Exception {
        if (!(valorRecebido == this.calcularPreco(visitor))){
            throw new Exception("Valor insuficiente");
        }

        for (Exame e : exames){
            e.setPago(true);
        }

        this.dataPagamento = new Date();
    }
}
