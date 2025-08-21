package org.example.Enum;

public enum Prioridade {
    URGENTE(3),
    POUCO_URGENTE(2),
    ROTINA(1);

    private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
