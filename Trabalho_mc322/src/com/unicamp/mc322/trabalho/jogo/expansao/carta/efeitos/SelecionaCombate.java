package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Bot;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Scanner;
import java.util.Random;

public class SelecionaCombate extends Efeito {
    public SelecionaCombate(){
        super(MomentosDoTurno.APOS_INVOCACAO);
        this.tipoEfeito = TipoEfeito.SelecionarCombate;
    }
    //uma unica vez apos o uso dessa carta, duas unidades ser?o escolhidas e entrar?o em conflito direto
    //nao precisa ser durante a rodada de ataque ou defesa

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int indiceMonstroInimigo;
        int indiceMonstroAliado;
        if(jogador.ehBot()){
            indiceMonstroAliado = ((Bot) jogador).getNumeroRandom(6) - 1;
            indiceMonstroInimigo = ((Bot) jogador).getNumeroRandom(6) - 1;
        }else{
            indiceMonstroAliado = interagirComUsuario1() - 1;
            indiceMonstroInimigo = interagirComUsuario2() - 1;
        }
        //escolhe o aliado e o inimigo e usa o comando atacar e defender respectivamente
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                if (jogador == mesa.getJogador1()) {
                    if(!mesa.getJogador2().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador1().getCartasEmCampo().get(indiceMonstroAliado).atacarCampo(mesa.getJogador2(), indiceMonstroInimigo);
                    }else{
                        System.out.println("N?o existe carta em campo para atacar");
                    }
                } else {
                    if(!mesa.getJogador1().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador2().getCartasEmCampo().get(indiceMonstroAliado).atacarCampo(mesa.getJogador1(), indiceMonstroInimigo);
                    }else{
                        System.out.println("N?o existe carta em campo para atacar");
                    }

                }
                if(!carta.ehFeitico()){
                    ((Monstro) carta).atacou();
                }

            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio atacara outro aleatorio\n");
            } finally {
                if (jogador == mesa.getJogador1()) {
                    if(!mesa.getJogador2().getCartasEmCampo().isEmpty()) {
                        int numeroCartasCampo1 = jogador.getNumeroCartasCampo();
                        int posicaoAleatoria1 = ((Bot) jogador).getNumeroRandom(numeroCartasCampo1);
                        int numeroCartasCampo2 = mesa.getJogador2().getCartasEmCampo().size();
                        int posicaoAleatoria2 = ((Bot) mesa.getJogador2()).getNumeroRandom(numeroCartasCampo2);
                        mesa.getJogador1().getCartasEmCampo().get(posicaoAleatoria1).atacarCampo(mesa.getJogador2(), (posicaoAleatoria2));
                    }else {
                        System.out.println("N?o existe carta em campo para atacar");
                    }
                }else{
                    if(!mesa.getJogador1().getCartasEmCampo().isEmpty()) {
                        int numeroCartasCampo2 = mesa.getJogador2().getCartasEmCampo().size();
                        int posicaoAleatoria2 = ((Bot) jogador).getNumeroRandom(numeroCartasCampo2);
                        int numeroCartasCampo1 = mesa.getJogador1().getCartasEmCampo().size();
                        int posicaoAleatoria1 = ((Bot) mesa.getJogador1()).getNumeroRandom(numeroCartasCampo1);
                        mesa.getJogador2().getCartasEmCampo().get(posicaoAleatoria2).atacarCampo(mesa.getJogador1(), (posicaoAleatoria1));
                    }else{
                        System.out.println("N?o existe carta em campo para atacar");
                    }
                }
                if(!carta.ehFeitico()){
                    ((Monstro) carta).atacou();
                }
            }
        }else{
            System.out.println("N?o existe carta em campo para atacar");
        }
    }

    public int interagirComUsuario1(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posi??o do campo(1,2,3,4,5,6) que o monstro aliado se encontra: ");
            return respostaUsuario.nextInt();
        }catch(Exception InputMisMatchException){
            System.out.println("Argumento invalido, tente Novamente");
            return interagirComUsuario1();
        }
    }
    public int interagirComUsuario2(){
        try{
            Scanner respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posi??o do campo(1,2,3,4,5,6) que o monstro inimigo se encontra: ");
            return respostaUsuario.nextInt();
        }catch(Exception InputMisMatchException){
            System.out.println("Argumento invalido, tente Novamente");
            return interagirComUsuario2();
        }
    }
}