package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class Sobrecarga extends Efeito {

    public Sobrecarga (){
        super(MomentosDoTurno.ANTES_BATALHA); /*causa o dano em excesso ao nexus adversario*/
        this.tipoEfeito = TipoEfeito.Sobrecarga;
    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta){
        int pos = jogador.getCartasBatalhando().indexOf(carta);
        if(jogador == mesa.getJogador1()){
            if(mesa.getJogador2().getCartasBatalhando().get(pos) != null) {
                if (((Monstro) carta).getAtaque() > mesa.getJogador2().getCartasBatalhando().get(pos).getVidaAtual()) {
                    int sobredano = ((Monstro) carta).getAtaque() - mesa.getJogador2().getCartasBatalhando().get(pos).getVidaAtual();
                    mesa.getJogador2().DiminuirVida(sobredano);
                }
            }
        }else{
            if(mesa.getJogador1().getCartasBatalhando().get(pos) != null) {
                if (((Monstro) carta).getAtaque() > mesa.getJogador1().getCartasBatalhando().get(pos).getVidaAtual()) {
                    int sobredano = ((Monstro) carta).getAtaque() - mesa.getJogador1().getCartasBatalhando().get(pos).getVidaAtual() ;
                    mesa.getJogador1().DiminuirVida(sobredano);
                }
            }
        }
    }
}
