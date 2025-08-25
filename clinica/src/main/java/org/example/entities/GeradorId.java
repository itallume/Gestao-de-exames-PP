package org.example.entities;

public class GeradorId {
    private static GeradorId instance = null;
    private int contador = 0;

    private GeradorId() {
    }

    public static GeradorId getInstance() {
        if (instance == null) {
            instance = new GeradorId();
        }
        return instance;
    }

    public int gerarId() {
        return ++contador;
    }
}
