package org.example.entities.models;

import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pagamento implements Desconto {
    private List<Exame> exames;
    private Date dataPagamento;
    private Paciente paciente;
    private static final Logger logger = LoggerFactory.getLogger(Pagamento.class);

    public Pagamento(List<Exame> exames, Paciente paciente) {
        this.exames = exames;
        this.paciente = paciente;
    }

    public Double calcularPreco(ExameVisitor<Double> visitor) {
        double preco = 0;
        for (Exame e : exames) {
            preco += e.calcularPreco(visitor);
        }
        return preco;
    }

    public void pagar() {
        for (Exame e : exames) {
            logger.info("paciente: " + paciente.getNome() + " - Tentando Pagar exame de Id: " + e.getId());
            System.out.println("Pagando exame de Id: " + e.getId());
            e.setPago(true);
            logger.info("paciente: " + paciente.getNome() + " - Pagou exame de Id: " + e.getId());
        }

        this.dataPagamento = new Date();
    }
}
