package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;

public class Monstro extends Carta {
    private int vidaMaxima;
    private int vidaAtual = vidaMaxima;
    private int ataque;
    private Traco traco; //o traço precisa ser criado no runner antes de ser colocado no construtor
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco, Efeito... efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.traco = traco;
    }

    @Override
    public void imprimirCarta() {
        System.out.printf("|<MONSTRO> Nome: %s vida: %d custo: %d efeitos:  |", this.getNome(), this.getNome(),this.getCusto());
    }
    public int getAtaque(){
        return ataque;
    }

    public int getVidaAtual(){
        return vidaAtual;
    }

    public void buffar(int ataque, int vida){
        this.ataque = this.ataque + ataque;
        this.vidaAtual = this.vidaAtual + vida;
        this.vidaMaxima = this.vidaMaxima + vida;
    }

    public void CurarFull(){
        vidaAtual = vidaMaxima;
    }
    public void alterarVidaAtual(int dano){
        vidaAtual-=dano;
    }
    public void atacar(Jogador jogadorDefensor,int indiceMonstro){
        Monstro monstroDefensor = jogadorDefensor.getCartasBatalhando().get(indiceMonstro);
        if(monstroDefensor!=null){
            int danoDefensor = monstroDefensor.getAtaque();
            monstroDefensor.alterarVidaAtual(ataque);
            alterarVidaAtual(danoDefensor);
        }else{
            jogadorDefensor.DiminuirVida(ataque);
        }
    }
    public void zerarAtk(){
        ataque = 0;
    }
    public void diminuirVida(int valor){
        vidaAtual = vidaAtual - valor;
    }
}