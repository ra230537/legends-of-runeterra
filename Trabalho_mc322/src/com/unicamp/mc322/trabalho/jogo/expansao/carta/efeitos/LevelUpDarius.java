package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.*;

public class LevelUpDarius extends Efeito {

    public LevelUpDarius(){
        super(MomentosDoTurno.APOS_BATALHA);
        this.tipoEfeito = TipoEfeito.LevelUpDarius;
    }

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta){
        if(jogador == mesa.getJogador1()){
            if(mesa.getJogador2().getVida() < 10 && !estaExpirado()){
                ((Monstro) carta).buffar(4,1);
                expirou();
            }
        }else{
            if(mesa.getJogador1().getVida() < 10 && !estaExpirado()) {
                ((Monstro) carta).buffar(4, 1);
                expirou();
            }
        }
    }
}
