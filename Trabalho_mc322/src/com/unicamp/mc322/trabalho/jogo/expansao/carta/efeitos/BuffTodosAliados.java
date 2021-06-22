package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

public class BuffTodosAliados extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffTodosAliados(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);//da a todos os aliados da messa ataque e defesa extra
        this.buffAtaque = buffAtaque;
        this.buffDefesa = buffDefesa;
    }
}
