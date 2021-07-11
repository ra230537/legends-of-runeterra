package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.*;

public class LevelUpGaren extends Efeito {

    public LevelUpGaren(){
        super(MomentosDoTurno.FIM_TURNO);
        this.tipoEfeito = TipoEfeito.LevelUpGaren;
    }

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta){
        if(((Monstro) carta).getQntAtaques() == 2 && !estaExpirado()){
            ((Monstro) carta).buffar(1,1);
            ((Monstro) carta).adicionarTraco(Traco.ELUSIVO);
            expirou();
        }
        ((Monstro) carta).CurarFull();
    }
}

