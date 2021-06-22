package com.unicamp.mc322.trabalho.jogo.expansao.carta;

public class Feitico extends Carta {
    //feitiços tem seus efeitos usados assim que são lançados ao campo
    private MomentosDoTurno momentoQueSeraLido = MomentosDoTurno.APOS_INVOCACAO;
    protected Feitico(String nomeCarta, int custo, Efeito... efeitos) {
        super(nomeCarta, custo, efeitos);
    }

}
