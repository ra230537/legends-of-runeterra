package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class Barreira extends Efeito {
    public Barreira(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }
}