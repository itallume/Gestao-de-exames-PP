package org.example.entities.validador;

import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameRaioX;

import java.util.Map;

public class ValidadorRaioX extends ValidadorLaboratorial {

    @Override
    public void validar(ExameProcedimento exame) throws Exception {
        if (exame instanceof ExameRaioX) {

            Map<String, String> dados = exame.getDados();

            String descricao = dados.get("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new Exception("A descrição do exame de Raio-X é obrigatória.");
            }

            String radiologista = dados.get("radiologistaAss");
            if (radiologista == null || radiologista.trim().isEmpty()) {
                throw new Exception("O radiologista responsável pelo Raio-X deve ser informado.");
            }

            String imagensBase64 = dados.get("imagens");
            if (imagensBase64 == null || imagensBase64.trim().isEmpty()) {
                throw new Exception("O exame de Raio-X deve conter pelo menos uma imagem.");
            }

            System.out.println("Validação do RaioX com imagens concluída.");
        }
        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
