package org.example.entities.validador;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameSangue;

import java.util.Map;

public class ValidadorColesterol extends ValidadorLaboratorial {

    @Override
    public void validar(ExameProcedimento exame) throws Exception {
        if (exame instanceof ExameSangue) {
            Map<String, String> dados = exame.getDados();

            if (dados.containsKey("colesterol")) {
                double valor;
                try {
                    valor = Double.parseDouble(dados.get("colesterol"));
                } catch (Exception e) {
                    throw new Exception("Dados para o campo colesterol inválidos.");
                }

                if (valor < 200) {
                    dados.put("colesterolDiagnostico", "Desejável");
                } else if (valor <= 239) {
                    dados.put("colesterolDiagnostico", "Limítrofe");
                } else {
                    dados.put("colesterolDiagnostico", "Alto");
                }
            }
            System.out.println("Validando colesterol...");
        }

        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
