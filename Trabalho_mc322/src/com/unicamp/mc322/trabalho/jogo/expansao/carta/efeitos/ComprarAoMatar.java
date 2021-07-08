package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class ComprarAoMatar extends Efeito {
    public ComprarAoMatar(){
        super(MomentosDoTurno.APOS_BATALHA);//compra uma carta se esse monstro destruir uma unidade inimiga
    }

    @Override
    public void usarEfeito(Mesa mesa) {
        /**
         * verifica se a unidade inimiga a frente está com 0 pontos de vida
         * se sim, compra uma carta
         */
    }
}