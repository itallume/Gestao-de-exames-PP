package org.example.entities.exame;

import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Map;

public class ExameColonoscopia extends ExameEndoscopico {

    @Override
    public void preencherDados(Map<String,String> dados) {
        this.setDados(dados);
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
        System.out.println("Realizando exame de Colonoscopia do paciente: " + getPaciente().getNome());
    }

}
