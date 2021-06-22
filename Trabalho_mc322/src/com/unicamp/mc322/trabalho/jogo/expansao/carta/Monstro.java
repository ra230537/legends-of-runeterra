package com.unicamp.mc322.trabalho.jogo.expansao.carta;

public class Monstro extends Carta {

    private int vidaMaxima;
    private int vidaAtual = vidaMaxima;
    private int ataque;
    private Traco traco; //o traço precisa ser criado no runner antes de ser colocado no construtor
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco, Efeito... efeitos){
        super(nomeCarta,custo, efeitos);
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.traco = traco;
    }
}