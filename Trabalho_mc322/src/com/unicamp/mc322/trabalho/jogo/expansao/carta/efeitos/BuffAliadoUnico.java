package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class BuffAliadoUnico extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffAliadoUnico(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//da a algum aliado escolhido pelo jogador ataque e defesa extra
}