package org.example.entities.exame;

import org.example.entities.abstracts.ExameLaboratorial;
import org.example.entities.interfaces.ExameVisitor;

import com.itextpdf.layout.Document;

import java.util.Map;

public class ExameRessonanciaMagnetica extends ExameLaboratorial {
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
        System.out.println("Realizando exame de Ressonancia Magnetica do paciente: "+ getPaciente().getNome());
    }

    @Override
    public void montarCorpoDocumento(Document doc, Map<String, String> dados) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'montarCorpoDocumento'");
    }


}
