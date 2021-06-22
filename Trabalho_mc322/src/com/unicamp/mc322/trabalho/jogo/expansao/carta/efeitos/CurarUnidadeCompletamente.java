package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class CurarUnidadeCompletamente extends Efeito {
    public CurarUnidadeCompletamente(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//restaura todos os pontos de defesa de uma unidade uma unica vez
}
