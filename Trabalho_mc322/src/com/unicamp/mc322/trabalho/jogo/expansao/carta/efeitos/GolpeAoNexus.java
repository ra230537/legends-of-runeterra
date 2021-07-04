package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class GolpeAoNexus extends Efeito {
    private int danoAoNexus;
    public GolpeAoNexus (int danoAoNexus){
        super(MomentosDoTurno.APOS_INVOCACAO); //causa um dano definido no construtor ao nexus

    }

    @Override
    public void usarEfeito() {
        //causa o dano que lhe é dado como atributo ao nexus inimigo
    }
}