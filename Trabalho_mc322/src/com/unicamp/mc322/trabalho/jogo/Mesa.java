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
		int campo1 = getJogador1().getCartasEmCampo().size();
		int batalha1 = getJogador1().getCartasBatalhando().size();
		int campo2 = getJogador2().getCartasEmCampo().size();
		int batalha2 = getJogador2().getCartasBatalhando().size();
		for(int i = 1; i <= campo1; i++){
			getJogador1().getCartasEmCampo().get(i).imprimirCarta(i);
			System.out.println();
		}
		for(int j = 1; j <= batalha1; j++){
			getJogador1().getCartasBatalhando().get(j).imprimirCarta(j);
			System.out.println();
		}
		for(int l = 1; l <= batalha2; l++){
			getJogador2().getCartasBatalhando().get(l).imprimirCarta(l);
			System.out.println();
		}
		for(int k = 1; k <= campo2; k++){
			getJogador2().getCartasEmCampo().get(k).imprimirCarta(k);
			System.out.println();
		}
	}
}
