package org.example.entities.carregador;

import java.util.ArrayList;
import java.util.List;
import org.example.entities.abstracts.CarregadorDadosCSV;
import org.example.entities.models.Paciente;

public class PacienteCarregador extends CarregadorDadosCSV {

    @Override
    public List<Paciente> parseDados(List<String> dados) {
        List<Paciente> pacientes = new ArrayList<>();
        System.out.println("Realizando parse de dados dos pacientes");

        for (String linha : dados) {
            if (linha.trim().isEmpty()) continue;

            String[] campos = linha.split(",");
            if (campos.length >= 6) {
                String nome = campos[0].trim();
                int idade = Integer.parseInt(campos[1].trim());
                String telefone = campos[2].trim();
                String email = campos[3].trim();
                String convenio = campos[4].trim().equals("null") ? null : campos[4].trim();
                String sexo = campos[5].trim();

                pacientes.add(new Paciente(nome, idade, telefone, email, convenio, sexo));
            }
        }

        return pacientes;
    }

    @Override
    public List<Paciente> processarDados(List records) {
        System.out.println("Realizando processamento de dados dos pacientes");
        return records;
    }

    @Override
    public List<Paciente> salvarDados(List records) {
        System.out.println("Salvando dados dos pacientes");
        return records;
    }
}
