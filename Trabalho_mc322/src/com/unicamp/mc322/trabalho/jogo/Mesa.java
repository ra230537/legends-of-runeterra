package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.ArrayList;

public class Mesa {
	//private Carta[][] CartasNaMesa = new Carta[4][6];
	private Jogador[] jogadores	= new Jogador[2];

	public Mesa(Jogador j1, Jogador j2) {
		jogadores[0] = j1;
		jogadores[1] = j2;
	}

	public Jogador getJogador1() {
		return jogadores[0];
	}

	public Jogador getJogador2() {
		return jogadores[1];
	}

	public Jogador[] getJogadores(){
		return jogadores;
	}

	public void imprimirMesa(){

	}
}
