package org.example.entities.carregador;

import java.util.ArrayList;
import java.util.List;
import org.example.entities.abstracts.CarregadorDadosCSV;
import org.example.entities.models.Medico;

public class MedicoCarregador extends CarregadorDadosCSV {

    @Override
    public List<Medico> parseDados(List<String> dados) {
        List<Medico> medicos = new ArrayList<>();
        System.out.println("Realizando parse de dados dos medicos");

        for (String linha : dados) {
            if (linha.trim().isEmpty()) continue;

            String[] campos = linha.split(",");
            if (campos.length >= 3) {
                String nome = campos[0].trim();
                String crm = campos[1].trim();
                String especialidade = campos[2].trim();

                medicos.add(new Medico(nome, crm, especialidade));
            }
        }

        return medicos;
    }

    @Override
    public List<Medico> processarDados(List records) {
        System.out.println("Realizando processamento de dados dos medicos");
        return records;
    }

    @Override
    public List<Medico> salvarDados(List records) {
        System.out.println("Salvando dados dos medicos");
        return records;
    }
}
