package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class GolpearTodos extends Efeito {
    public GolpearTodos(){
        super(MomentosDoTurno.APOS_INVOCACAO);
    }//da a oportunidade de algum aliado conseguir realizar esse feito uma unica vez

    @Override
    public void usarEfeito() {
        int resposta = interagirComUsuario();
        //para cada monstro no campo inimigo, cause o equivalente ao ataque do monstro escolhido de dano
    }
    private int interagirComUsuario(){
        Scanner respostausuario = new Scanner(System.in);
        System.out.print("Digite a posição do monstro aliado que realizará o ataque: ");
        return respostausuario.nextInt();
    }
}