package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Deck {
	private String nome;
	private Carta[] deck = new Carta[40];
	
	public Deck(String nome) {
		this.nome = nome;
	}
}
