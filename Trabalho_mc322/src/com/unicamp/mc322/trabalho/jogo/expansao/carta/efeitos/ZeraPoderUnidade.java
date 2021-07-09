package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Scanner;

public class ZeraPoderUnidade extends Efeito {

    public ZeraPoderUnidade(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//zera o ataque de uma unidade inimiga por um turno
    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        String respostaAliadoOuInimigo = primeiraPergunta();
        int indiceMonstro = segundaPergunta();
        /**se a primeira resposta for a
         * escolhe um aliado de indice "indiceMonstro" e zera permanentemente seu ataque
         * se nao,
         * escolhe um inimog de indice "indiceMonstro" e zera permanentemente seu ataque
         */
        if(respostaAliadoOuInimigo.equals('a')){
            if(jogador == mesa.getJogador1()){
                mesa.getJogador1().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
            }else{
                mesa.getJogador2().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
            }
        }else{
            if(jogador == mesa.getJogador1()){
                mesa.getJogador2().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
            }else{
                mesa.getJogador1().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
            }
        }
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