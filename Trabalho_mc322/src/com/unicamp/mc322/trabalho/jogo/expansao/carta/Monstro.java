package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;

import java.util.ArrayList;
import java.util.Collections;

public class Monstro extends Carta {
    protected boolean campeao = false;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataque;
    private int qntAtaques = 0;
    private ArrayList<Traco> listatracos = new ArrayList<>(); //o traco precisa ser criado no runner antes de ser colocado no construtor
    private int ataqueFuria = 0;
    private int vidaFuria = 0;

    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Traco traco2, Traco traco3, int ataqueFuria, int vidaFuria, Efeito... efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.ataque = ataque;
        this.vidaAtual = vidaMaxima;
        listatracos.add(traco1);
        listatracos.add(traco2);
        listatracos.add(traco3);
        this.ataqueFuria = ataqueFuria;
        this.vidaFuria = vidaFuria;
    }
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, Efeito... efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        listatracos.add(traco1);
    }
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1){
        super(nomeCarta,custo, false);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        listatracos.add(traco1);
    }
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, int ataqueFuria, int vidaFuria, Efeito... efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        listatracos.add(traco1);
        this.ataqueFuria = ataqueFuria;
        this.vidaFuria = vidaFuria;
    }
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, Traco traco1, int ataqueFuria, int vidaFuria){
        super(nomeCarta,custo, false);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        listatracos.add(traco1);
        this.ataqueFuria = ataqueFuria;
        this.vidaFuria = vidaFuria;
    }

    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, ArrayList<Traco> tracos, int ataqueFuria, int vidaFuria, ArrayList<Efeito> efeitos){
        super(nomeCarta,custo, false,efeitos);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        this.listatracos = tracos;
        this.ataqueFuria = ataqueFuria;
        this.vidaFuria = vidaFuria;
    }
    public Monstro(String nomeCarta, int custo, int vidaMaxima, int ataque, ArrayList<Traco> tracos, int ataqueFuria, int vidaFuria){
        super(nomeCarta,custo, false);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        this.listatracos = tracos;
        this.ataqueFuria = ataqueFuria;
        this.vidaFuria = vidaFuria;
    }

    protected String getTextoTracos() {
        String info = "Tracos: ";

        for (int i = 0; i < listatracos.size(); i++) {
            info += listatracos.get(i);
            if ((i + 1) % 3 == 0) {
                info = "\n\r|";
            } else if (i != getListaEfeitos().size() - 1) {
                info += ", ";
            }
        }

        return info;
    }

    @Override
    public void imprimirCartaDetalhada() {
        String info = "|<MONSTRO> NOME: " + this.getNome() + " Custo: " + this.getCusto() + " Regiao: " + this.getRegiao() + "\n\r";
        info += "|Vida atual/vida maxima: " + this.getVidaAtual() + "/" + this.getVidaMaxima() + " Atk: " + this.getAtaque() + " Custo: " + this.getCusto() + "\n\r";
        info += "|" + this.getTextoEfeitos() + "\n\r";
        info += "|" + this.getTextoTracos();
        System.out.println(info);
    }

    @Override
    public void imprimirNome() {
        System.out.print("|<MONSTRO> NOME: " + this.getNome()+ "|");
    }

    public void imprimirCarta(int posicao){
        System.out.print("  |" + posicao + " " + this.getNome() + "[" + this.getAtaque() + "][" + this.getVidaAtual() + "] " + "(" + this.getCusto() + ")" + "|");
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

    public ArrayList<Traco> getTracos() {
        return listatracos;
    }

    public int getAtaqueFuria() {
        return ataqueFuria;
    }

    public int getVidaFuria() {
        return vidaFuria;
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
            if(listatracos.contains(Traco.ELUSIVO)){
                if(monstroDefensor.listatracos.contains(Traco.ELUSIVO)){
                    monstroDefensor.alterarVidaAtual(ataque);
                    return;
                }else{
                    jogadorDefensor.DiminuirVida(ataque);
                    return;
                }
            }

            monstroDefensor.alterarVidaAtual(ataque);

            //tratar traço furia
            if(listatracos.contains(Traco.FURIA) && monstroDefensor.getVidaAtual() <= 0){
                buffar(ataqueFuria,vidaFuria);
            }
            alterarVidaAtual(danoDefensor);

            //tratar traço ataqueduplo
            if(listatracos.contains(Traco.ATAQUEDUPLO) && monstroDefensor.getVidaAtual() <= 0 && getVidaAtual() > 0){
                jogadorDefensor.DiminuirVida(ataque);
                return;
            }else if(listatracos.contains(Traco.ATAQUEDUPLO) && getVidaAtual() > 0){
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
    public boolean ehCampeao() {
        return campeao;
    }
    public int getQntAtaques(){
        return qntAtaques;
    }
    public void adicionarTraco(Traco traco){
        listatracos.add(traco);
    }
}