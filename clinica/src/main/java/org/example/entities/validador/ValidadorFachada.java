package org.example.entities.validador;

import org.example.entities.interfaces.IValidador;

public class ValidadorFachada {

    public IValidador getChainExameImagem() {
        ValidadorRaioX vRaioX = new ValidadorRaioX();
        ValidadorRessonanciaMagnetica vRessonancia = new ValidadorRessonanciaMagnetica();
        vRaioX.setProximo(vRessonancia);
        return vRaioX;
    }

    public IValidador getChainExameEndoscopico() {
        ValidadorColonoscopia vColonoscopia = new ValidadorColonoscopia();
        ValidadorEndoscopiaDigestivaAlta vEndoscopiaAlta = new ValidadorEndoscopiaDigestivaAlta();
        vColonoscopia.setProximo(vEndoscopiaAlta);
        return vColonoscopia;
    }

    public IValidador getChainExameLaboratorial() {
        ValidadorColesterol vColesterol = new ValidadorColesterol();
        ValidadorCreatinina vCreatinina = new ValidadorCreatinina();
        ValidadorGlicose vGlicose = new ValidadorGlicose();
        vColesterol.setProximo(vCreatinina).setProximo(vGlicose);
        return vColesterol;
    }
}
