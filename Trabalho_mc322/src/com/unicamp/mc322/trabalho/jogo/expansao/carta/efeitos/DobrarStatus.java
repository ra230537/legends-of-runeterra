package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;


import java.util.Random;
import java.util.Scanner;

public class DobrarStatus extends Efeito {
    public DobrarStatus(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.DobrarStatus;
    }//dobra o status de alguma unidade aliada

    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //dobra a vida e o poder do monstro escolhido
        Random pos = new Random();
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                int atk = jogador.getCartasEmCampo().get(resposta - 1).getAtaque();
                int hp = jogador.getCartasEmCampo().get(resposta - 1).getVidaAtual();
                jogador.getCartasEmCampo().get(resposta - 1).buffar(atk, hp);
            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio sera buffado\n");
            } finally {
                int atk = jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).getAtaque();
                int hp = jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).getVidaAtual();
                jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).buffar(atk, hp);
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