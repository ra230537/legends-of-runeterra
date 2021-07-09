package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.Scanner;

public class GolpearTodos extends Efeito {
    public GolpearTodos(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//da a oportunidade de algum aliado conseguir realizar esse feito uma unica vez

    @Override
    public void usarEfeito(Jogador jogador, Mesa mesa, Carta carta) {
        int resposta = interagirComUsuario();
        //para cada monstro no campo inimigo, cause o equivalente ao ataque do monstro escolhido de dano
        int i = 0;
        int dano = ((Monstro) carta).getAtaque();
        if(jogador == mesa.getJogador1()) {
            int n = mesa.getJogador1().getCartasBatalhando().size();
            while (n >= 0) {
                mesa.getJogador2().getCartasEmCampo().get(i).diminuirVida(dano);
                i++;
            }
        }else{
            int n = mesa.getJogador1().getCartasBatalhando().size();
            while (n >= 0) {
                mesa.getJogador1().getCartasEmCampo().get(i).diminuirVida(dano);
                i++;
            }
        }

    }
    private int interagirComUsuario(){
        Scanner respostausuario = new Scanner(System.in);
        System.out.print("Digite a posição do monstro aliado que realizará o ataque: ");
        return respostausuario.nextInt();
    }
}