package org.example.entities;

import org.example.entities.abstracts.ExameLaboratorial;
import org.example.entities.interfaces.ExameVisitor;

import java.util.HashMap;
import java.util.Map;

public class ExameSangue extends ExameLaboratorial {

    public ExameSangue() {
        super();
    }

    @Override
    public void preencherDados(Map<String, String> dados) {

    }

    @Override
    public Object gerarLaudo() {
        return null;
    }

    @Override
    public <T> T aceitar(ExameVisitor<T> visitor) {
        return visitor.visitarExame(this);
    }

    @Override
    public void realizarExame() {
        System.out.println("Realizando exame de sangue do paciente: "+ getPaciente().getNome());
    }
}
