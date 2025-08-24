package org.example.entities.desconto;

import org.example.Enum.Campanha;
import org.example.entities.interfaces.Desconto;
import org.example.entities.models.Exame;
import org.example.entities.models.Paciente;
import org.example.entities.models.Pagamento;

import java.util.List;

public class DescontoFachada {

    public static Desconto verificarDescontosPossiveis(List<Exame> exames, Paciente paciente,
            List<Campanha> campanhasAtivas) {
        Desconto desconto = new Pagamento(exames, paciente);
        if (paciente.getIdade() > 60) {
            desconto = new DescontoIdoso(desconto);
        }

        if (paciente.getConvenio() != null) {
            desconto = new DescontoConvenio(desconto);
        }

        for (Campanha campanha : campanhasAtivas) {
            switch (campanha) {
                case NOVEMBRO_AZUL:
                    desconto = new DescontoNovembroAzul(desconto);
                    break;
                case OUTUBRO_ROSA:
                    desconto = new DescontoOutubroRosa(desconto);
                    break;
                case SETEMBRO_AMARELO:
                    throw new UnsupportedOperationException("Método não implementado");
            }
        }

        return desconto;
    }
}
