package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;

import java.util.ArrayList;
import java.util.Collections;

public class Monstro extends Carta {
    private int vidaMaxima;
    private int vidaAtual = vidaMaxima;
    private int ataque;
    private int qntAtaques;
    private ArrayList<Traco> listatracos = new ArrayList<>(); //o traço precisa ser criado no runner antes de ser colocado no construtor

    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Traco traco2, Traco traco3, Efeito... efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        listatracos.add(traco1);
        listatracos.add(traco2);
        listatracos.add(traco3);
    }

    private void completar

    public void imprimirCartaDetalhada() {
        String info = "|<MONSTRO> NOME: " + this.getNome() + " Custo: " + this.getCusto() + " Regiao: " + this.getRegiao() + "\n\r";
        info += "|Vida atual/vida máxima: " + this.getVidaAtual() + "/" + this.getVidaMaxima() + " Atk: " + this.getAtaque() + " Custo: " + this.getCusto() + "\n\r";
        info += "|Efeitos:" + "\n\r";
        info += "|Tracos:" + "\n\r";
        System.out.println(info);
    }
    public int getAtaque(){
        return ataque;
    }

    public int getVidaAtual(){
        return vidaAtual;
    }
    public int getVidaMaxima(){
        return vidaMaxima;
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
        atacou();
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
    public void atacou(){ qntAtaques++; }

    public int getQntAtaques(){
        return qntAtaques;
    }
}