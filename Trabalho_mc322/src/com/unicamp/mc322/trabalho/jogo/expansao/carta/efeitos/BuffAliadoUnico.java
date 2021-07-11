package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;

import java.util.Random;
import java.util.Scanner;

public class BuffAliadoUnico extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffAliadoUnico(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.BuffAliadoUnico;
        this.buffAtaque = buffAtaque;
        this.buffDefesa = buffDefesa;
    }//da a algum aliado escolhido pelo jogador ataque e defesa extra

    @Override
    public String getTipoEfeito() {
        return tipoEfeito.toString() + "(BUFFATK: " + buffAtaque + "/BUFFDEF: " + buffDefesa + ")";
    }

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //incrementa a vida e o poder da carta que receber esse buff
        Random pos = new Random();
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                jogador.getCartasEmCampo().get(resposta - 1).buffar(buffAtaque, buffDefesa);
            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio sera buffado\n");
            } finally {
                jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).buffar(buffAtaque, buffDefesa);
            }
        }else{
            System.out.println("Não existe carta em campo para ser buffada");
        }

    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição do aliado que receberá o buff: ");
        return respostaUsuario.nextInt();
    }
}