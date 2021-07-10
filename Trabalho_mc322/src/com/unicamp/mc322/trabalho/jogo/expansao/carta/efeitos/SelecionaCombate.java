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
        super(MomentosDoTurno.ANTES_BATALHA);
        this.tipoEfeito = TipoEfeito.SelecionarCombate;
    }
    //uma unica vez apos o uso dessa carta, duas unidades serão escolhidas e entrarão em conflito direto
    //nao precisa ser durante a rodada de ataque ou defesa

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int indiceMonstroAliado = interagirComUsuario1();
        int indiceMonstroInimigo = interagirComUsuario2();
        Random pos = new Random();
        //escolhe o aliado e o inimigo e usa o comando atacar e defender respectivamente
        try{
            if(jogador == mesa.getJogador1()){
                mesa.getJogador1().getCartasEmCampo().get(indiceMonstroAliado).atacar(mesa.getJogador2(), indiceMonstroInimigo);
            }else{
                mesa.getJogador2().getCartasEmCampo().get(indiceMonstroAliado).atacar(mesa.getJogador1(), indiceMonstroInimigo);
            }
            ((Monstro) carta).atacou();
        }catch(Exception NullPointerException){
            System.out.print("Posicao Invalida, um monstro aleatorio atacara outro aleatorio\n");
        }finally{
            if(jogador == mesa.getJogador1()){
                mesa.getJogador1().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).atacar(mesa.getJogador2(), pos.nextInt(jogador.getCartasEmCampo().size()));
            }else{
                mesa.getJogador2().getCartasEmCampo().get(pos.nextInt(jogador.getCartasEmCampo().size())).atacar(mesa.getJogador1(), pos.nextInt(jogador.getCartasEmCampo().size()));
            }
            ((Monstro) carta).atacou();
        }

    }
    public int interagirComUsuario1(){
        // imprimir a mesa
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição que o monstro aliado se encontra: ");
        return respostaUsuario.nextInt();
    }
    public int interagirComUsuario2(){
        // imprimir a mesa
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Digite a posição que o monstro inimigo se encontra: ");
        return respostaUsuario.nextInt();
    }
}