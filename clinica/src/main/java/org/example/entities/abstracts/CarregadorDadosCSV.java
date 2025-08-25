package org.example.entities.abstracts;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CarregadorDadosCSV {

    private static final Logger logger = LoggerFactory.getLogger(CarregadorDadosCSV.class);

    public List<String> carregarDados(String resourcePath) {
        List<String> dados = new ArrayList<>();
        System.out.println("Carregando dados de: " + resourcePath);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Recurso não encontrado no classpath: " + resourcePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    dados.add(linha);
                }
            }
            System.out.println("Carregadas " + dados.size() + " linhas");
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }

        return dados;
    }

    public void fecharArquivo(File arq) {
        System.out.println("Fechando arquivo: " + arq.getPath());
    }

    public abstract List parseDados(List<String> dados);
    public abstract List processarDados(List records);
    public abstract List salvarDados(List records);
}
