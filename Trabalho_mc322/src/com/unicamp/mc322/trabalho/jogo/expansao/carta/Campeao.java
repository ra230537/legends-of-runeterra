package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;

public class Campeao extends Monstro {

    public Campeao(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Traco traco2, Traco traco3, int ataqueFuria, int vidaFuria, Efeito... efeitos){
        super(nomeCarta, custo, vidaMaxima, ataque, traco1, traco2, traco3, ataqueFuria, vidaFuria, efeitos);
        this.campeao = true;
    }
    public Campeao(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Efeito... efeitos){
        super(nomeCarta, custo, vidaMaxima, ataque, traco1, efeitos);
        this.campeao = true;
    }
    public Campeao(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, int ataqueFuria, int vidaFuria, Efeito... efeitos){
        super(nomeCarta, custo, vidaMaxima, ataque, traco1, ataqueFuria, vidaFuria, efeitos);
        this.campeao = true;
    }

    public Campeao(String nomeCarta, int custo, int vidaMaxima, int ataque, ArrayList<Traco> tracos, int ataqueFuria, int vidaFuria, ArrayList<Efeito> efeitos){
        super(nomeCarta, custo, vidaMaxima, ataque, tracos, ataqueFuria, vidaFuria, efeitos);
        this.campeao = true;
    }

    @Override
    public void imprimirCartaDetalhada() {
        String info = "|<CAMPEAO> NOME: " + this.getNome() + " Custo: " + this.getCusto() + " Regiao: " + this.getRegiao() + "\n\r";
        info += "|Vida atual/vida máxima: " + this.getVidaAtual() + "/" + this.getVidaMaxima() + " Atk: " + this.getAtaque() + " Custo: " + this.getCusto() + "\n\r";
        info += "|Efeitos:" + this.getTextoEfeitos() + "\n\r";
        info += "|Tracos:" + this.getTextoTracos();
        System.out.println(info);
    }

    @Override
    public void imprimirNome() {
        System.out.print("|<CAMPEAO> NOME: " + this.getNome()+ "|");
    }
}
