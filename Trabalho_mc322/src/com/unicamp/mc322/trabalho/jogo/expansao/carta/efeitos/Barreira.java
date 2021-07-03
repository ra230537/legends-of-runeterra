package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class Barreira extends Efeito {
    public Barreira(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }
    //da a algum aliado um escudo que iompede que esse monstro sofra dano no proximo round
    //obs:caso um monstro com barreira receba outra barreira, o efeito se extender� por mais um round
    //deve ser perguntado ao monstro invocado qual aliado que ele quer usar a barreira
}