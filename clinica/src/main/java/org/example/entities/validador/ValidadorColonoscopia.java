package org.example.entities.validador;

import org.example.entities.abstracts.ExameImagem;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.abstracts.ValidadorImagem;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameColonoscopia;

import java.util.Map;

public class ValidadorColonoscopia extends ValidadorLaboratorial {

    @Override
    public void validar(ExameProcedimento exame) throws Exception {

        if (exame instanceof ExameColonoscopia) {

            Map<String, String> dados = exame.getDados();

            String descricao = dados.get("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new Exception("A descrição da Colonoscopia é obrigatória.");
            }

            String endoscopista = dados.get("endoscopista");
            if (endoscopista == null || endoscopista.trim().isEmpty()) {
                throw new Exception("O endoscopista responsável pelo exame deve ser informado.");
            }

            String preparoIntestinal = dados.get("preparoIntestinal");
            if (preparoIntestinal == null || preparoIntestinal.trim().isEmpty()) {
                dados.put("preparoIntestinal", "Não informado");
            }
            System.out.println("Validação da Colonoscopia concluída.");
        }


        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
