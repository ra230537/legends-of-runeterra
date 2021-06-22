package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class DobrarStatus extends Efeito {
    public DobrarStatus(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//dobra o status de alguma unidade aliada
}