package org.example.entities;

import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorLaboratorial;

import java.util.Map;

public class ValidadorGlicose extends ValidadorLaboratorial {

    @Override
    public void validar(ExameTipo exame) throws Exception {
        if (exame instanceof ExameSangue) {
            Map<String, String> dados = exame.getDados();

            if (dados.containsKey("glicose")) {
                double valor;
                try {
                    valor = Double.parseDouble(dados.get("glicose"));
                } catch (Exception e) {
                    throw new Exception("Dados para o campo glicose inválidos.");
                }

                if (valor < 60) {
                    dados.put("glicoseDiagnostico", "Hipoglicemia");
                } else if (valor <= 99) {
                    dados.put("glicoseDiagnostico", "Normal");
                } else if (valor <= 125) {
                    dados.put("glicoseDiagnostico", "Intolerante à Glicose");
                } else {
                    dados.put("glicoseDiagnostico", "Diabetes");
                }
            }
            System.out.println("Validando glicose...");
        }

        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
