package org.example.entities.carregador;

import java.util.ArrayList;
import java.util.List;

import org.example.entities.abstracts.CarregadorDadosCSV;

public class PacienteCarregador extends CarregadorDadosCSV {

    @Override
    public List<Record> parseDados(List<String> dados) {
        List<Record> records = new ArrayList<Record>();
        System.out.println("Realizando parse de dados dos pacientes");
        return records;
    }

    @Override
    public List<Record> processarDados(List<Record> records) {
        System.out.println("Realizando processamento de dados dos pacientes");
        return records;
    }

    @Override
    public List<Record> salvarDados(List<Record> records) {
        System.out.println("Salvando dados dos pacientes");
        return records;
    }
    
}
