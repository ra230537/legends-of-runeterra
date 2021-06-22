package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class CartaRestituida extends Efeito {
    public CartaRestituida(){
        super(MomentosDoTurno.APOS_RODADA_ATAQUE); //sera lida quando a carta for destruida,pode ser no ataque ou defesa
    }
}