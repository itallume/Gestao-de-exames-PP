package org.example.entities;

import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.INotificador;

import java.util.HashMap;
import java.util.Map;

public class ExameColonoscopia extends ExameEndoscopico{

    @Override
    public void preencherDados(Map dados) {

    }

    @Override
    public Object gerarLaudo() {
        return null;
    }

    @Override
    public <T> T aceitar(ExameVisitor<T> visitor) {
        return visitor.VisitarExame(this);
    }

    @Override
    public void realizarExame() {
        System.out.println("Realizando exame de Colonoscopia do paciente: "+ getPaciente().getNome());
    }
}
