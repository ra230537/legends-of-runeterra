package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class AtacaNexus extends Efeito {
    public AtacaNexus(){
        super(MomentosDoTurno.ANTES_ATAQUE);
    }//da a alguma unidade a possibilidade de golpear o nexus inimigo diretamente
}
