package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.Stack;

public class Deck {
	private String nome;
	private Stack <Carta> deck = new Stack<Carta>();
	//private Carta[] deck = new Carta[40];
	
	public Deck(String nome) {
		this.nome = nome;
	}
	
	public void setNovoNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getNome() {
		return nome;
	}

	public Carta tirarCartaTopo(){
		return deck.pop();
	}
}
