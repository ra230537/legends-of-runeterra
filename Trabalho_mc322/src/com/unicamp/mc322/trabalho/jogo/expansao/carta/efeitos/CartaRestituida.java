package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class CartaRestituida extends Efeito {
    public CartaRestituida(){
        super(MomentosDoTurno.APOS_RODADA_ATAQUE); //sera lida quando a carta for destruida,pode ser no ataque ou defesa
        this.tipoEfeito = TipoEfeito.CartaRestituida;
    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {

        if( ((Monstro)carta).getVidaAtual() <= 0){
            jogador.puxarCarta();
        }
    }
}