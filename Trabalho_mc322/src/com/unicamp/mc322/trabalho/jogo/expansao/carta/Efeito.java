package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos.TipoEfeito;

//ESSA CLASSE QUE APLICA OS EFEITOS, O BOARD MANAGER APENAS GERENCIA
public abstract class Efeito {
    protected TipoEfeito tipoEfeito;
    private boolean expirado = false;
    private MomentosDoTurno momentoQueSeraLido;


    protected Efeito(MomentosDoTurno momentoQueSeraLido) {
        this.momentoQueSeraLido = momentoQueSeraLido;
    }

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {

    }


    public MomentosDoTurno getMomentoQueSeraLido() {
        return momentoQueSeraLido;
    }

    public String getTipoEfeito() {
        return tipoEfeito.toString();
    }

}