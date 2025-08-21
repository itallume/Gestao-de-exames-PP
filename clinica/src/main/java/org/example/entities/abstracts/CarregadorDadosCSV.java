package org.example.entities.abstracts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class CarregadorDadosCSV {
    
    public List<String> carregarDados(String resourcePath){
        List<String> dados = new ArrayList<String>();
        System.out.println("Carregando dados de: " + resourcePath);
        return dados;
    }

    public void fecharArquivo(File arq){
        System.out.println("Fechando arquivo: " + arq.getPath());
    }

    public abstract List<Record> parseDados(List<String> dados);
    public abstract List<Record> processarDados(List<Record> records);
    public abstract List<Record> salvarDados(List<Record> records);
    
}
