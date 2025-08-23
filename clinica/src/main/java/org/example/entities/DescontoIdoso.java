package org.example.entities;

import org.example.entities.abstracts.DescontoBase;
import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;

//Decorator concreto
public class DescontoIdoso extends DescontoBase {

    @Override
    public Double calcularPreco(ExameVisitor<Double> visitor) {

        double valor = super.calcularPreco(visitor);
        return valor - (valor * 0.08);
    }
}
