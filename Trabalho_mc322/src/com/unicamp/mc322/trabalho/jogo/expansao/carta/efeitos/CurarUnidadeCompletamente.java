package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Bot;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;


import java.util.Random;
import java.util.Scanner;

public class CurarUnidadeCompletamente extends Efeito {
    public CurarUnidadeCompletamente(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.CurarUnidadeCompletamente;
    }//restaura todos os pontos de defesa de uma unidade uma unica vez
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta;
        if(jogador.ehBot()){
            resposta = ((Bot) jogador).getNumeroRandom(6);
        }else {
            resposta = interagirComUsuario();
        }
        //faz vida_atual do monstro ser igual a vida_maxima
        Random pos = new Random();
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                jogador.getCartasEmCampo().get(resposta - 1).CurarFull();
            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio sera curado\n");
            } finally {
                jogador.getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).CurarFull();
            }
        }else{
            System.out.println("Não existe carta em campo para ser curada");
        }

    }
    private int interagirComUsuario(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posição(1,2,3,4,5,6) do aliado que receberá o buff: ");
            return respostaUsuario.nextInt();
        }catch(Exception InputMisMatchException){
            System.out.println("Tente Novamente");
            return interagirComUsuario();
        }
    }
}