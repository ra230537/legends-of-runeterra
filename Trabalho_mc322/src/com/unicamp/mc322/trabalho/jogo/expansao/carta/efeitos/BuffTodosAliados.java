package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class BuffTodosAliados extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffTodosAliados(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);//da a todos os aliados da messa ataque e defesa extra
        this.buffAtaque = buffAtaque;
        this.buffDefesa = buffDefesa;
    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        //da a todos as cartas da mesa um bonus de ataque e defesa
        int i = 0;
        int n = jogador.getCartasEmCampo().size();
        while(n >= 0) {
            jogador.getCartasEmCampo().get(i).buffar(buffAtaque, buffDefesa);
            i++;
        }
    }

}