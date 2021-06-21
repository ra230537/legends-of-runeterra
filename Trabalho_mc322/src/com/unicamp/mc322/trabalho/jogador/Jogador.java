package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Jogador {
	private int vida;
	private int mana;
	private int manaFeitico;
	private Carta[] mao = new Carta[10]; //na mão é possivel ter no maximo 10 cartas, qualquer outra carta retirada do baralho sera descartada;
	private Carta[] baralho= new Carta[40];
	
	public Jogador() {
		vida = 20;
		mana = 1;
		manaFeitico = 0;
	}
	
	

}
