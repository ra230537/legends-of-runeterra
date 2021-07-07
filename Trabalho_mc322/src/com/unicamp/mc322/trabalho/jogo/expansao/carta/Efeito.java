package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import com.unicamp.mc322.trabalho.jogo.Mesa;

//ESSA CLASSE QUE APLICA OS EFEITOS, O BOARD MANAGER APENAS GERENCIA
public abstract class Efeito {
    private boolean expirado = false;
    private MomentosDoTurno momentoQueSeraLido;
    
    protected Efeito(MomentosDoTurno momentoQueSeraLido){
        this.momentoQueSeraLido = momentoQueSeraLido;    
    }

    public void usarEfeito(Mesa mesa) {

    }

    public MomentosDoTurno getMomentoQueSeraLido() {
        return momentoQueSeraLido;
    }
}