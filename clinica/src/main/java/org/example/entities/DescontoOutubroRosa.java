package org.example.entities;

import org.example.entities.interfaces.ExameVisitor;

public class DescontoOutubroRosa extends DescontoConvenio{

    @Override
    public Double calcularPreco(ExameVisitor<Double> visitor) {

        double valor = super.calcularPreco(visitor);
        return valor - (valor * 0.15);
    }
}
