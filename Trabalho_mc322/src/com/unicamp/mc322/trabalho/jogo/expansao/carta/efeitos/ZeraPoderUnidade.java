package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class ZeraPoderUnidade extends Efeito {

    public ZeraPoderUnidade(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//zera o ataque de uma unidade inimiga por um turno
}