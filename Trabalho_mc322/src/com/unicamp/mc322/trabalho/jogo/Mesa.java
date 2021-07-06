package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Mesa {
	private Carta[][] CartasNaMesa = new Carta[4][6];
	private Jogador[] jogadores	= new Jogador[2];

	public Mesa(Jogador J1, Jogador J2) {
		jogadores[0] = J1;
		jogadores[1] = J2;
	}

	public Carta[][] getCartasNaMesa() {
		return CartasNaMesa;
	}

	public Jogador[] getJogadores() {
		return jogadores;
	}

}
