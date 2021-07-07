package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class Barreira extends Efeito {

    public Barreira(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }
    //da a algum aliado um escudo que iompede que esse monstro sofra dano no proximo round
    //obs:caso um monstro com barreira receba outra barreira, o efeito se extenderá por mais um round
    //deve ser perguntado ao monstro invocado qual aliado que ele quer usar a barreira
    @Override
    //passar o mapa como parametro pras cartas. porque ai elas vao ter conhecimento do jogo inteiro
    public void usarEfeito(Mesa mesa) {
        mesa.imprimirMesa();
        int resposta = interagirComUsuario();
        //torna o atributo temBarreira na carta escolhida como true por uma rodada
        try{
            //saber qual dos jogadores jogou a carta pra saber em qual arraylist ir pra aplicar o efeito
        }catch(Exception NullPointerException) {
            System.out.print("Posição Invalida\n");
        }

    }
    private int interagirComUsuario(){
        Scanner respostaUsuario = null;
        try {
            respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posição do aliado que receberá barreira! ");
        } catch (Exception IllegalArgumentException) {
            System.out.print("Tipo invalido\n");
        }
        return respostaUsuario.nextInt();
    }
}