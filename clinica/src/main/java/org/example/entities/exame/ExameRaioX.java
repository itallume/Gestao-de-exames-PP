package org.example.entities.exame;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.entities.abstracts.ExameImagem;
import org.example.entities.interfaces.ExameVisitor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExameRaioX extends ExameImagem {

    @Override
    public void preencherDados(Map<String, String> dados) {
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
        System.out.println("Realizando exame de RaioX do paciente: " + getPaciente().getNome());
    }

}
