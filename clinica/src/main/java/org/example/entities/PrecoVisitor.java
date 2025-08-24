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
    public Double visitarExame(ExameColonoscopia exameColonoscopia) {
        return 400.0;
    }

    @Override
    public Double visitarExame(ExameRessonanciaMagnetica exameRessonanciaMagnetica) {
        return 320.0;
    }

    @Override
    public Double visitarExame(ExameEndoscopiaDigestivaAlta exameEndoscopiaDigestivaAlta) {
        return 450.0;
    }
}
