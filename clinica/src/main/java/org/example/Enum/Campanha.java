package org.example.Enum;

public enum Campanha {
    NOVEMBRO_AZUL(3),
    OUTUBRO_ROSA(2),
    SETEMBRO_AMARELO(1);


    private final int valor;

    Campanha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
