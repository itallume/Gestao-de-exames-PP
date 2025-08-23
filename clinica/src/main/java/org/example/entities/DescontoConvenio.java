package org.example.entities;

import org.example.entities.abstracts.DescontoBase;
import org.example.entities.interfaces.ExameVisitor;

public class DescontoConvenio extends DescontoBase {

    @Override
    public Double calcularPreco(ExameVisitor<Double> visitor) {

        double valor = super.calcularPreco(visitor);
        return valor - (valor * 0.15);
    }
}
