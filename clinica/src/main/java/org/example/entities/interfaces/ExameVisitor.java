package org.example.entities.interfaces;

import org.example.entities.ExameColonoscopia;
import org.example.entities.ExameRaioX;
import org.example.entities.ExameSangue;

public interface ExameVisitor<T> {
    T visitarExame(ExameSangue exameSangue);
    T visitarExame(ExameRaioX exameRaioX);
    T VisitarExame(ExameColonoscopia exameColonoscopia);


}
