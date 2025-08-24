package org.example.entities;

import org.example.entities.abstracts.DescontoBase;
import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;

public class DescontoOutubroRosa extends DescontoBase {

    public DescontoOutubroRosa(Desconto wrappee) {
        super(wrappee);
    }

    @Override
    public Double calcularPreco(ExameVisitor<Double> visitor) {

        double valor = super.calcularPreco(visitor);
        return valor - (valor * 0.15);
    }
}
