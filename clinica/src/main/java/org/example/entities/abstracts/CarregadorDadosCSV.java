package org.example.entities.abstracts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

public abstract class CarregadorDadosCSV {
    private static final Logger logger = LoggerFactory.getLogger(CarregadorDadosCSV.class);

    public List<String> carregarDados(String resourcePath) {
        List<String> dados = new ArrayList<String>();
        System.out.println("Carregando dados de: " + resourcePath);
        logger.info("Carregando dados de: " + resourcePath);
        return dados;
    }

    public void fecharArquivo(File arq) {
        System.out.println("Fechando arquivo: " + arq.getPath());
    }

    public abstract List<Record> parseDados(List<String> dados);

    public abstract List<Record> processarDados(List<Record> records);

    public abstract List<Record> salvarDados(List<Record> records);

}
