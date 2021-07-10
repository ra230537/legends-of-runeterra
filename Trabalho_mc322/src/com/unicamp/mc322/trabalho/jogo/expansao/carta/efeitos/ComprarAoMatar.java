package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class ComprarAoMatar extends Efeito {
    public ComprarAoMatar(){
        super(MomentosDoTurno.APOS_BATALHA);//compra uma carta se esse monstro destruir uma unidade inimiga
        this.tipoEfeito = TipoEfeito.ComprarAoMatar;
    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        /*
         * verifica se a unidade inimiga a frente está com 0 pontos de vida
         * se sim, adiciona uma carta poro a sua mao
         */
        int pos = jogador.getCartasBatalhando().indexOf(carta);
        if(((Monstro) carta).getVidaAtual() >= 0){
            if(jogador == mesa.getJogador1()){
                if(mesa.getJogador2().getCartasBatalhando().get(pos).getVidaAtual() <= 0){
                    mesa.getJogador1().addCartaMao(new Monstro("Poro", 1, 1, 2, null,null));
                }
            }else{
                if(mesa.getJogador1().getCartasBatalhando().get(pos).getVidaAtual() <= 0) {
                    mesa.getJogador2().addCartaMao(new Monstro("Poro", 1, 1, 2, null,null));
                }
            }
        }

    }
}