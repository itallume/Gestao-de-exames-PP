package org.example.entities.interfaces;

public interface Desconto {
    Double calcularPreco(ExameVisitor<Double> visitor);
    void pagar();
}
