package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;

import java.util.Scanner;

public class BuffAliadoUnico extends Efeito {
    int buffAtaque;
    int buffDefesa;
    public BuffAliadoUnico(int buffAtaque,int buffDefesa){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//da a algum aliado escolhido pelo jogador ataque e defesa extra

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //incrementa a vida e o poder da carta que receber esse buff
        jogador.getCartasEmCampo().get(resposta-1).buffar(buffAtaque, buffDefesa);


    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição do aliado que receberá o buff: ");
        return respostaUsuario.nextInt();
    }
}