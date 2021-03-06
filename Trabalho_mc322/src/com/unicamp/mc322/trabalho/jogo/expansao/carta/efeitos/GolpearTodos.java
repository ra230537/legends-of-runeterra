package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Bot;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;


import java.util.Random;
import java.util.Scanner;

public class GolpearTodos extends Efeito {
    public GolpearTodos(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.GolpearTodos;
    }//da a oportunidade de algum aliado conseguir realizar esse feito uma unica vez

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta;
        if(jogador.ehBot()){
            resposta = ((Bot) jogador).getNumeroRandom(6);
        }else {
            resposta = interagirComUsuario();
        }

        //para cada monstro no campo inimigo, cause o equivalente ao ataque do monstro escolhido de dano

        Random pos = new Random();
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                int dano = jogador.getCartasEmCampo().get(resposta-1).getAtaque();
                if (jogador == mesa.getJogador1()) {
                    int n = mesa.getJogador1().getCartasBatalhando().size();
                    for (int i = 0; i < n; i++) {
                        mesa.getJogador2().getCartasEmCampo().get(i).diminuirVida(dano);
                    }
                } else {
                    int n = mesa.getJogador2().getCartasBatalhando().size();
                    for (int i = 0; i < n; i++) {
                        mesa.getJogador1().getCartasEmCampo().get(i).diminuirVida(dano);
                    }
                }
            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio atacara todos os inimigos\n");
            } finally {
                int dano = jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).getAtaque();
                if (jogador == mesa.getJogador1()) {
                    int n = mesa.getJogador1().getCartasBatalhando().size();
                    for (int i = 0; i < n; i++) {
                        mesa.getJogador2().getCartasEmCampo().get(i).diminuirVida(dano);
                    }
                } else {
                    int n = mesa.getJogador2().getCartasBatalhando().size();
                    for (int i = 0; i < n; i++) {
                        mesa.getJogador1().getCartasEmCampo().get(i).diminuirVida(dano);
                    }
                }
            }
        }else{
            System.out.println("N?o existe carta em campo para atacar os monstros inimigos");
        }



    }
    private int interagirComUsuario(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posi??o(1,2,3,4,5,6) do monstro aliado que realizara o ataque: ");
            return respostaUsuario.nextInt();
        }catch(Exception InputMisMatchException){
            System.out.println("Tente Novamente");
            return interagirComUsuario();
        }
    }
}