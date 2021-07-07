package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.Stack;

public class Deck {
	private String nome;
	private Estado estado = Estado.Inutilizavel;
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

	public Estado getEstado() {
		return estado;
	}

	public void imprimirQuantidade() {
		System.out.printf("%d/40", deck.size());
	}

	public void imprimirDeck() {
		System.out.println("Nome do deck: " + nome + "Estado: " + estado);
		for(int i = 0; i < deck.size(); i++) {
			deck.get(i).imprimirCarta();
			if((i+1) % 3 == 0) {
				System.out.println();
			}
		}
	}

	public void removerCarta(String nomeCarta) {

	}

	public void addCarta(String nomeCarta) {

	}

	public Carta tirarCartaTopo(){
		return deck.pop();
	}
}
