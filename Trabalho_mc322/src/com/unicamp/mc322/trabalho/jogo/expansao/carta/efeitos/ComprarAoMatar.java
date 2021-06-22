package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class ComprarAoMatar extends Efeito {
    public ComprarAoMatar(){
        super(MomentosDoTurno.APOS_ATACAR);//compra uma carta se esse monstro destruir uma unidade inimiga
    }
}