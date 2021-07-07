package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class SelecionaCombate extends Efeito {
    public SelecionaCombate(){
        super(MomentosDoTurno.ANTES_ATAQUE);
    }
    //uma unica vez apos o uso dessa carta, duas unidades serão escolhidas e entrarão em conflito direto
    //nao precisa ser durante a rodada de ataque ou defesa

    @Override
    public void usarEfeito(Mesa mesa) {
        int indiceMonstroAliado = interagirComUsuario1();
        int indiceMonstroInimigo = interagirComUsuario2();
        //escolhe o aliado e o inimigo e usa o comando atacar e defender respectivamente
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