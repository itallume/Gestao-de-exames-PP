package org.example.entities.interfaces;

import org.example.entities.exame.*;

public interface ExameVisitor<T> {
    T visitarExame(ExameSangue exameSangue);
    T visitarExame(ExameRaioX exameRaioX);
    T visitarExame(ExameColonoscopia exameColonoscopia);
    T visitarExame(ExameRessonanciaMagnetica exameRessonanciaMagnetica);
    T visitarExame(ExameEndoscopiaDigestivaAlta exameEndoscopiaDigestivaAlta);
}
