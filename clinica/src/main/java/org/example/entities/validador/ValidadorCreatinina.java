package org.example.entities.validador;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameSangue;
import org.example.entities.models.Paciente;

import java.util.Map;

public class ValidadorCreatinina extends ValidadorLaboratorial {

    @Override
    public void validar(ExameProcedimento exame) throws Exception {
        if (exame instanceof ExameSangue) {
            Map<String, String> dados = exame.getDados();

            if (dados.containsKey("creatinina")) {
                double valor;
                try {
                    valor = Double.parseDouble(dados.get("creatinina"));
                } catch (Exception e) {
                    throw new Exception("Dados para o campo creatinina inválidos.");
                }

                Paciente paciente = exame.getPaciente();
                int idade = paciente.getIdade();
                String sexo = paciente.getSexo();

                if (idade > 60 && valor > 1.5) {
                    dados.put("creatininaDiagnostico", "Risco Elevado (Idoso)");
                } else if ("masculino".equalsIgnoreCase(sexo) && valor > 1.3) {
                    dados.put("creatininaDiagnostico", "Alerta (Homem)");
                } else if ("feminino".equalsIgnoreCase(sexo) && valor > 1.1) {
                    dados.put("creatininaDiagnostico", "Alerta (Mulher)");
                } else if (idade <= 12 && valor > 0.8) {
                    dados.put("creatininaDiagnostico", "Investigar (Criança)");
                } else {
                    dados.put("creatininaDiagnostico", "Normal");
                }
            }
            System.out.println("Validando creatinina...");
        }

        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
