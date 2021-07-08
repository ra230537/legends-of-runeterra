package com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.Scanner;

public class AtacaNexus extends Efeito {

	public AtacaNexus(){

        super(MomentosDoTurno.ANTES_BATALHA);
    }//da a alguma unidade a possibilidade de golpear o nexus inimigo diretamente
    //deve ser usada antes do ataque do monstro, deve ser peerguntado a ele qual alvo deseja atacar

    @Override
    //passar o mapa como parametro pras cartas. porque ai elas vao ter conhecimento do jogo inteiro
    public void usarEfeito(Jogador atacante, Mesa mesa, Carta carta) {
	    if(atacante == mesa.getJogador1()) {
            String resposta = interagirComUsuario();
            if (resposta.equals("s")){
                //ataca diretamente o nexus inimigo ao inves da carta que está defendendo
                mesa.getJogador2().DiminuirVida(((Monstro) carta).getAtaque());
            }else {
                /* verifica se há campeão defensor
                 * se sim, a carta atacante usa a função atacar enquanto a defensora usa a função defender
                 * se não, ataca o nexus
                 */
                if (mesa.getJogador2().getCartasBatalhando().isEmpty()) {
                    mesa.getJogador2().DiminuirVida(((Monstro) carta).getAtaque());
                } else if (((Monstro) carta).getAtaque() >= mesa.getJogador2().getCartasBatalhando().getVidaAtual()){ //tentando puxar lista de cartas ao inves de uma carta
                    ((Monstro) carta).getVidaAtual() = ((Monstro) carta).getVidaAtual() - mesa.getJogador2().getCartasBatalhando().getAtaque();
                    mesa.getJogador2().getCartasBatalhando().getVidaAtual() = 0;
                }
            }
        }else {
            String resposta = interagirComUsuario();
            if (resposta.equals("s")){
                //ataca diretamente o nexus inimigo ao inves da carta que está defendendo
                mesa.getJogador1().DiminuirVida(((Monstro) carta).getAtaque());
            }else {
                /* verifica se há campeão defensor
                 * se sim, a carta atacante usa a função atacar enquanto a defensora usa a função defender
                 * se não, ataca o nexus
                 */
                if (mesa.getJogador1().getCartasBatalhando().isEmpty()) {
                    mesa.getJogador1().DiminuirVida(((Monstro) carta).getAtaque());
                } else if (((Monstro) carta).getAtaque() >= mesa.getJogador1().getCartasBatalhando().getVidaAtual()){
                    ((Monstro) carta).getVidaAtual() = ((Monstro) carta).getVidaAtual() - mesa.getJogador1().getCartasBatalhando().getAtaque();
                    mesa.getJogador1().getCartasBatalhando().getVidaAtual() = 0;
                }
            }

        }


    }
    private String interagirComUsuario(){
        Scanner respostaUsuario = new Scanner(System.in);
        System.out.println("Deseja atacar diretamente o nexus inimigo? (y/n): ");
        return respostaUsuario.nextLine();
    }
}