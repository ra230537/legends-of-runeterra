package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;

public class GolpeAoNexus extends Efeito {
    private int danoAoNexus;
    public GolpeAoNexus (int danoAoNexus){
        super(MomentosDoTurno.APOS_INVOCACAO); //causa um dano definido no construtor ao nexus

    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        //causa o dano que lhe � dado como atributo ao nexus inimigo
        if(jogador == mesa.getJogador1()){
            mesa.getJogador2().DiminuirVida(danoAoNexus);
        }else{
            mesa.getJogador1().DiminuirVida(danoAoNexus);
        }
    }
}