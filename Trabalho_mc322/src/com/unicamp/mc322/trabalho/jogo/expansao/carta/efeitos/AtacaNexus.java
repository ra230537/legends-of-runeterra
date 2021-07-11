package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;


public class AtacaNexus extends Efeito {

	public AtacaNexus(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.AtacarNexus;
    }

    /*Uma unidade evocada ataca o Nexus do adversario*/

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        if(jogador == mesa.getJogador1()){
            mesa.getJogador2().DiminuirVida(((Monstro) carta).getAtaque());
        }else{
            mesa.getJogador1().DiminuirVida(((Monstro) carta).getAtaque());
        }
        ((Monstro) carta).atacou();
    }

}