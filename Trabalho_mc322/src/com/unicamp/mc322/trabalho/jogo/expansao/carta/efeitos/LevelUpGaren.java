package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class LevelUpGaren extends Efeito {

    public LevelUpGaren(){
        super(MomentosDoTurno.APOS_BATALHA);
    }

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta){
        if(((Monstro) carta).getQntAtaques() >= 2){
            ((Monstro) carta).buffar(1,1);
            ((Monstro) carta).adicionarTraco(Traco.ELUSIVO);
        }
    }
}
