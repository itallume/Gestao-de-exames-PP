package org.example.entities.abstracts;

import org.example.entities.interfaces.Desconto;
import org.example.entities.interfaces.ExameVisitor;

// Base Decorator
public class DescontoBase implements Desconto{
    private Desconto wrappee;

    public DescontoBase(Desconto wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public Double calcularPreco(ExameVisitor<Double> visitor) {
        return wrappee.calcularPreco(visitor);
    }

    @Override
    public void pagar() {
        wrappee.pagar();
    }

}
