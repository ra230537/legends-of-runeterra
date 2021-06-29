package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Mesa {
	private Carta[][] CartasNaMesa = new Carta[3][5];
	private Jogador[] jogadores	= new Jogador[1];
	
	public Mesa(Jogador J1, Jogador J2) {
		
		jogadores[0] = J1;
		jogadores[1] = J2;
	}
	
	public void JogarCarta(Carta c, Jogador J) {
		
	}
}
