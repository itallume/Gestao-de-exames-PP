package org.example.entities;

import org.example.entities.interfaces.ExameVisitor;

public class PrecoVisitor implements ExameVisitor<Double> {
    @Override
    public Double visitarExame(ExameSangue exameSangue) {
        return 15.00;
    }

    @Override
    public Double visitarExame(ExameRaioX exameRaioX) {
        return 70.0;
    }

    @Override
    public Double VisitarExame(ExameColonoscopia exameColonoscopia) {
        return 400.0;
    }
}
