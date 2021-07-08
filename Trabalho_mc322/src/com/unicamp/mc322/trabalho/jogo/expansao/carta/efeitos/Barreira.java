package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;


import java.util.Scanner;

public class Barreira extends Efeito {

    public Barreira(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }
    //da a algum aliado um escudo que iompede que esse monstro sofra dano no proximo round
    //obs:caso um monstro com barreira receba outra barreira, o efeito se extender? por mais um round
    //deve ser perguntado ao monstro invocado qual aliado que ele quer usar a barreira
    @Override
    //passar o mapa como parametro pras cartas. porque ai elas vao ter conhecimento do jogo inteiro
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        jogador.ImprimirCartasEmCampo();
        int resposta = interagirComUsuario();
        //torna o atributo temBarreira na carta escolhida como true por uma rodada
        try{
            jogador.getCartasEmCampo().get(resposta-1).SetTemBarreira();
        }catch(Exception NullPointerException) {
            System.out.print("Posicao Invalida\n");
    }

}
    private int interagirComUsuario(){
        Scanner respostaUsuario = null;
        try {
            respostaUsuario = new Scanner(System.in);
            System.out.println("Digite a posicao do aliado que ira receber a barreira! ");
        } catch (Exception IllegalArgumentException) {
            System.out.print("Tipo invalido\n");
        }
        return respostaUsuario.nextInt();
    }
}