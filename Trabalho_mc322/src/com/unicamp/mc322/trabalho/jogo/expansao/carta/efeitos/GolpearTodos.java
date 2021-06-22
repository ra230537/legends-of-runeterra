package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class GolpearTodos extends Efeito {
    public GolpearTodos(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//da a oportunidade de algum aliado conseguir realizar esse feito uma unica vez
}