package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Random;
import java.util.Scanner;

public class ZeraPoderUnidade extends Efeito {

    public ZeraPoderUnidade(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.ZeraPoderUnidade;
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
        Random pos = new Random();

        if(respostaAliadoOuInimigo.equals('a')){
            try{
                if(jogador == mesa.getJogador1()){
                    mesa.getJogador1().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
                }else{
                    mesa.getJogador2().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
                }
            }catch(Exception NullPointerException){
                System.out.print("Posicao Invalida, um monstro aleatorio focara com poder zerado\n");
            }finally{
                if(jogador == mesa.getJogador1()){
                    mesa.getJogador1().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).zerarAtk();
                }else{
                    mesa.getJogador2().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).zerarAtk();
                }
            }

        }else{
            try{
                if(jogador == mesa.getJogador1()){
                    mesa.getJogador2().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
                }else{
                    mesa.getJogador1().getCartasEmCampo().get(indiceMonstro-1).zerarAtk();
                }
            }catch(Exception NullPointerException){
                System.out.print("Posicao Invalida, um monstro aleatorio ficara com poder zerado\n");
            }finally{
                if(jogador == mesa.getJogador1()){
                    mesa.getJogador2().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).zerarAtk();
                }else{
                    mesa.getJogador1().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).zerarAtk();
                }
            }
        }
    }
    public String primeiraPergunta(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("deseja escolher um aliado ou inimigo? (a/i) ");
            return respostaUsuario.nextLine();
        }catch(Exception InputMisMatchException){
            System.out.println("Argumento Invalido, tente Novamente");
            return primeiraPergunta();
        }
    }
    public int segundaPergunta(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posição(1,2,3,4,5,6) que o monstro se encontra: ");
            return respostaUsuario.nextInt();
        }catch(Exception InputMisMatchException){
            System.out.println("Argumento invalido, tente Novamente");
            return segundaPergunta();
        }
    }

}