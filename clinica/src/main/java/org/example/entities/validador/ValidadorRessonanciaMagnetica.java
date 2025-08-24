package org.example.entities.validador;

import java.util.Map;

import org.example.entities.abstracts.ExameTipo;
import org.example.entities.abstracts.ValidadorLaboratorial;
import org.example.entities.exame.ExameRessonanciaMagnetica;

public class ValidadorRessonanciaMagnetica extends ValidadorLaboratorial {

    @Override
    public void validar(ExameTipo exame) throws Exception {

        if (exame instanceof ExameRessonanciaMagnetica) {

            Map<String, String> dados = exame.getDados();

            String descricao = dados.get("descricao");
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new Exception("A descrição do exame de Ressonância Magnética é obrigatória.");
            }

            String radiologista = dados.get("radiologistaAss");
            if (radiologista == null || radiologista.trim().isEmpty()) {
                throw new Exception("O radiologista responsável pelo exame deve ser informado.");
            }

            String protocolo = dados.get("protocolo");
            if (protocolo == null || protocolo.trim().isEmpty()) {
                throw new Exception("O protocolo do exame de Ressonância Magnética deve ser informado.");
            }

            String contraste = dados.get("contraste");
            String dosagem = dados.get("dosagemContraste");
            if (contraste != null && !contraste.trim().isEmpty() && (dosagem == null || dosagem.trim().isEmpty())) {
                throw new Exception("A dosagem do contraste deve ser informada se o contraste foi utilizado.");
            }
        }
        System.out.println("Validação da Ressonância Magnética concluída.");

        if (proximo != null) {
            proximo.validar(exame);
        }
    }
}
