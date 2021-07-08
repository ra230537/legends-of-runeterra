package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Scanner;

public class CurarUnidadeCompletamente extends Efeito {
    public CurarUnidadeCompletamente(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//restaura todos os pontos de defesa de uma unidade uma unica vez
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //faz vida_atual do monstro ser igual a vida_maxima
        jogador.getCartasEmCampo().get(resposta-1).CurarFull();
    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição do aliado que receber a cura: ");
        return respostaUsuario.nextInt();
    }
}