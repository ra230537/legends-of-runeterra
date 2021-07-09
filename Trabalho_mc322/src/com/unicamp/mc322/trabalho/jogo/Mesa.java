package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.ArrayList;

public class Mesa {
	//private Carta[][] CartasNaMesa = new Carta[4][6];
	private ArrayList[] cartasNaMesa = new ArrayList[4]; //o maximo de cartas na mesa é 6
	private Jogador[] jogadores	= new Jogador[2];

	public Mesa(Jogador j1, Jogador j2) {
		jogadores[0] = j1;
		jogadores[1] = j2;
		//posiçaão na mesa em que se encontram as cartas em campo do jogador 1
		cartasNaMesa[0] = j1.getCartasEmCampo();
		//posicao da mesa em que se encontram as cartas em campo do jogador 2
		cartasNaMesa[3] = j2.getCartasEmCampo();
	}

	public ArrayList[] getCartasNaMesa() {
		return cartasNaMesa;
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
