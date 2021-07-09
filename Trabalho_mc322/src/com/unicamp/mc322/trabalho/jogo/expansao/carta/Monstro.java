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
    private ArrayList<Traco> listatracos = new ArrayList<>(); //o tra�o precisa ser criado no runner antes de ser colocado no construtor
    private int ataqueFuria;
    private int vidaFuria;

    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Traco traco2, Traco traco3, int ataqueFuria, int vidaFuria, Efeito... efeitos){
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
        info += "|Vida atual/vida m�xima: " + this.getVidaAtual() + "/" + this.getVidaMaxima() + " Atk: " + this.getAtaque() + " Custo: " + this.getCusto() + "\n\r";
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

            //tratar traço elusivo
            if(listatracos.contains(ELUSIVO)){
                if(monstroDefensor.listatracos.contains(ELUSIVO)){
                    monstroDefensor.alterarVidaAtual(ataque);
                    return;
                }else{
                    jogadorDefensor.DiminuirVida(ataque);
                    return;
                }
            }

            monstroDefensor.alterarVidaAtual(ataque);

            //tratar traço furia
            if(listatracos.contains(FURIA) && monstroDefensor.getVidaAtual() <= 0){
                buffar(ataqueFuria,vidaFuria);
            }
            alterarVidaAtual(danoDefensor);

            //tratar traço ataqueduplo
            if(listatracos.contains(ATAQUEDUPLO) && monstroDefensor.getVidaAtual() <= 0 && getVidaAtual() > 0){
                jogadorDefensor.DiminuirVida(ataque);
                return;
            }else if(listatracos.contains(ATAQUEDUPLO) && getVidaAtual() > 0){
                monstroDefensor.alterarVidaAtual(ataque);
                alterarVidaAtual(danoDefensor);
                return;
            }

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
    public void adicionarTraco(Traco traco){
        listatracos.add(traco);
    }
}