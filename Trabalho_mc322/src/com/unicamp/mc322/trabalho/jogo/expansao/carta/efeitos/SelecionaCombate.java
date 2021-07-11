package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

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
    //uma unica vez apos o uso dessa carta, duas unidades ser�o escolhidas e entrar�o em conflito direto
    //nao precisa ser durante a rodada de ataque ou defesa

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int indiceMonstroAliado = interagirComUsuario1();
        int indiceMonstroInimigo = interagirComUsuario2();
        Random pos = new Random();
        //escolhe o aliado e o inimigo e usa o comando atacar e defender respectivamente
        if(!jogador.getCartasEmCampo().isEmpty()) {
            try {
                if (jogador == mesa.getJogador1()) {
                    if(!mesa.getJogador2().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador1().getCartasEmCampo().get(indiceMonstroAliado).atacar(mesa.getJogador2(), indiceMonstroInimigo);
                    }else{
                        System.out.println("N�o existe carta em campo para atacar");
                    }
                } else {
                    if(!mesa.getJogador1().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador2().getCartasEmCampo().get(indiceMonstroAliado).atacar(mesa.getJogador1(), indiceMonstroInimigo);
                    }else{
                        System.out.println("N�o existe carta em campo para atacar");
                    }

                }
                ((Monstro) carta).atacou();
            } catch (Exception NullPointerException) {
                System.out.print("Posicao Invalida, um monstro aleatorio atacara outro aleatorio\n");
            } finally {
                if (jogador == mesa.getJogador1()) {
                    if(!mesa.getJogador2().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador1().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).atacar(mesa.getJogador2(), pos.nextInt(jogador.getCartasEmCampo().size()));
                    }else {
                        System.out.println("N�o existe carta em campo para atacar");
                    }
                }else{
                    if(!mesa.getJogador1().getCartasEmCampo().isEmpty()) {
                        mesa.getJogador2().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).atacar(mesa.getJogador1(), pos.nextInt(jogador.getCartasEmCampo().size()));
                    }else{
                        System.out.println("N�o existe carta em campo para atacar");
                    }
                }
                ((Monstro) carta).atacou();
            }
        }else{
            System.out.println("N�o existe carta em campo para atacar");
        }
    }
    public int interagirComUsuario1(){


        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posi��o que o monstro aliado se encontra: ");
        return respostaUsuario.nextInt();
    }
    public int interagirComUsuario2(){

        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posi��o que o monstro inimigo se encontra: ");
        return respostaUsuario.nextInt();
    }
}