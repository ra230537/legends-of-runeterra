package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class CartaRestituida extends Efeito {
    public CartaRestituida(){
        super(MomentosDoTurno.APOS_RODADA_ATAQUE); //sera lida quando a carta for destruida,pode ser no ataque ou defesa
    }

    @Override
    public void usarEfeito(Mesa mesa) {
        /**
         * verifique se a carta está com os pontos de vida iguais a 0
         * se sim, ative o efeito dela e o seu dono puxa uma carta do baralho
         *
         */
    }
}