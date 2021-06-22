package com.unicamp.mc322.trabalho.jogo.expansao.carta;

public abstract class Efeito {
    private boolean expirado = false;
    private MomentosDoTurno momentoQueSeraLido;
    
    protected Efeito(MomentosDoTurno momentoQueSeraLido){
        this.momentoQueSeraLido = momentoQueSeraLido;    
    }
}