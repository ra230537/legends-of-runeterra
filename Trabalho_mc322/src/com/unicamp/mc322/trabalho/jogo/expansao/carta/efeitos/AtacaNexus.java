package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.Scanner;

public class AtacaNexus extends Efeito {
	public AtacaNexus(){
        super(MomentosDoTurno.ANTES_ATAQUE);
    }//da a alguma unidade a possibilidade de golpear o nexus inimigo diretamente
    //deve ser usada antes do ataque do monstro, deve ser peerguntado a ele qual alvo deseja atacar

    @Override
    //passar o mapa como parametro pras cartas. porque ai elas vao ter conhecimento do jogo inteiro
    public void usarEfeito(Mesa mesa) {
        String resposta = interagirComUsuario();
        if (resposta.equals("s")){
            //ataca diretamente o nexus inimigo ao inves da carta que est� defendendo
        }else{
            /* verifica se h� campe�o defensor
             * se sim, a carta atacante usa a fun��o atacar enquanto a defensora usa a fun��o defender
             * se n�o, ataca o nexus
             */
        }

    }
    private String interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Deseja atacar diretamente o nexus inimigo? (y/n): ");
        return respostaUsuario.nextLine();
    }
}