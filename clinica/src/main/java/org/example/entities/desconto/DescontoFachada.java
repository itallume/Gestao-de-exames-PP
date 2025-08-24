package org.example.entities;

import org.example.entities.interfaces.Desconto;
import org.example.entities.models.Exame;
import org.example.entities.models.Paciente;
import org.example.entities.models.Pagamento;

import java.util.List;

public class DescontoFachada {

    public static Desconto verificarDescontosPossiveis(List<Exame> exames, Paciente paciente){
        Desconto desconto = new Pagamento(exames, paciente);
        if (paciente.getIdade() > 60){
            desconto = new DescontoIdoso(desconto);
        }

        if (paciente.getConvenio() != null){
            desconto = new DescontoConvenio(desconto);
        }
        return desconto;
    }
}
