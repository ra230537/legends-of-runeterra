package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class ZeraPoderUnidade extends Efeito {

    public ZeraPoderUnidade(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//zera o ataque de uma unidade inimiga por um turno
    @Override
    public void usarEfeito() {
        String respostaAliadoOuInimigo = primeiraPergunta();
        int indiceMonstro = segundaPergunta();
        /**se a primeira resposta for a
         * escolhe um aliado de indice "indiceMonstro" e zera permanentemente seu ataque
         * se nao,
         * escolhe um inimog de indice "indiceMonstro" e zera permanentemente seu ataque
         */
    }
    public String primeiraPergunta(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("deseja escolher um aliado ou inimigo? (a/i) ");
        return respostaUsuario.nextLine();
    }
    public int segundaPergunta(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição que o monstro se encontra: ");
        return respostaUsuario.nextInt();
    }

}