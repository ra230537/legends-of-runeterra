package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class BuffTodosAliados extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffTodosAliados(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);//da a todos os aliados da messa ataque e defesa extra
        this.buffAtaque = buffAtaque;
        this.buffDefesa = buffDefesa;
    }

    @Override
    public void usarEfeito() {
        //da a todos as cartas da mesa um bonus de ataque e defesa
    }

}