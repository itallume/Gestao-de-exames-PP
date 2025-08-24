package org.example.entities.validador;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameEndoscopiaDigestivaAlta;

import java.util.Map;

public class ValidadorEndoscopiaDigestivaAlta extends ValidadorLaboratorial {

    @Override
    public void validar(ExameProcedimento exame) throws Exception {

        if (exame instanceof ExameEndoscopiaDigestivaAlta) {

            Map<String, String> dados = exame.getDados();

            String descricao = dados.get("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new Exception("A descrição da Endoscopia Digestiva Alta é obrigatória.");
            }

            String endoscopista = dados.get("endoscopista");
            if (endoscopista == null || endoscopista.trim().isEmpty()) {
                throw new Exception("O endoscopista responsável pelo exame deve ser informado.");
            }

            String sedacao = dados.get("sedacao");
            if (sedacao == null || sedacao.trim().isEmpty()) {
                dados.put("sedacao", "Não informada");
            }
        }

        System.out.println("Validação da Endoscopia Digestiva Alta concluída.");

        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
