package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Scanner;

public class DobrarStatus extends Efeito {
    public DobrarStatus(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//dobra o status de alguma unidade aliada

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //dobra a vida e o poder do monstro escolhido
        int atk = jogador.getCartasEmCampo().get(resposta-1).getAtaque();
        int hp = jogador.getCartasEmCampo().get(resposta-1).getVidaAtual();
        jogador.getCartasEmCampo().get(resposta-1).buffar(atk, hp);
    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição do aliado que receberá o buff: ");
        return respostaUsuario.nextInt();
    }
}