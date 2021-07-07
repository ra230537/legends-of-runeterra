package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class CurarUnidadeCompletamente extends Efeito {
    public CurarUnidadeCompletamente(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//restaura todos os pontos de defesa de uma unidade uma unica vez
    public void usarEfeito(Mesa mesa) {
        int resposta = interagirComUsuario();
        //faz vida_atual do monstro ser igual a vida_maxima
    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição do aliado que receber a cura: ");
        return respostaUsuario.nextInt();
    }
}