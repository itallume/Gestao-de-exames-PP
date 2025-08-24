package org.example.entities;

import lombok.Data;
import org.example.entities.abstracts.ExameImagem;
import org.example.entities.interfaces.ExameVisitor;
import org.example.entities.interfaces.INotificador;

import java.util.Map;

@Data
public class ExameRaioX extends ExameImagem{

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
        System.out.println("Realizando exame de RaioX do paciente: "+ getPaciente().getNome());
    }
}
