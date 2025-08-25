package org.example.entities.validador;

import org.example.entities.abstracts.ExameEndoscopico;
import org.example.entities.abstracts.ExameImagem;
import org.example.entities.abstracts.ExameProcedimento;
import org.example.entities.interfaces.IValidador;
import org.example.entities.models.ExameOrdem;

public class ValidadorFachada {

    public static IValidador getValidador(ExameOrdem exame) {
        if (exame.getExameTipo() instanceof ExameEndoscopico) {
            return ValidadorFachada.getChainExameEndoscopico();
        } else if (exame.getExameTipo() instanceof ExameImagem) {
            return ValidadorFachada.getChainExameImagem();
        } else {
            return ValidadorFachada.getChainExameLaboratorial();
        }
    }

    public static IValidador getChainExameImagem() {
        ValidadorRaioX vRaioX = new ValidadorRaioX();
        ValidadorRessonanciaMagnetica vRessonancia = new ValidadorRessonanciaMagnetica();
        vRaioX.setProximo(vRessonancia);
        return vRaioX;
    }

    public static IValidador getChainExameEndoscopico() {
        ValidadorColonoscopia vColonoscopia = new ValidadorColonoscopia();
        ValidadorEndoscopiaDigestivaAlta vEndoscopiaAlta = new ValidadorEndoscopiaDigestivaAlta();
        vColonoscopia.setProximo(vEndoscopiaAlta);
        return vColonoscopia;
    }

    public static IValidador getChainExameLaboratorial() {
        ValidadorColesterol vColesterol = new ValidadorColesterol();
        ValidadorCreatinina vCreatinina = new ValidadorCreatinina();
        ValidadorGlicose vGlicose = new ValidadorGlicose();
        vColesterol.setProximo(vCreatinina).setProximo(vGlicose);
        return vColesterol;
    }
}
