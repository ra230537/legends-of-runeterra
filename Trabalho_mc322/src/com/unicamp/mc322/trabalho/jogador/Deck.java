package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

public class Deck {
	private String nome;
	private Estado estado = Estado.Inutilizavel;
	private Stack <Carta> deckStack = new Stack<Carta>();
	//private Map<String, Carta> deckMap = new HashMap<String, Carta>(); Permite encontrar carta pelo nome com mais facilidade
	
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
		System.out.printf("%d/40", deckStack.size());
	}

	public void imprimirDeck() {
		System.out.println("Nome do deck: " + nome + "Estado: " + estado);
		for(int i = 0; i < deckStack.size(); i++) {
			deckStack.get(i).imprimirCarta();
			if((i+1) % 3 == 0) {
				System.out.println();
			}
		}
	}

	public void removerCarta(String nomeCarta) {


	}

	public void addCarta(Carta novaCarta) {
		if(deckStack.size() == 40) {
			throw new GameException("Deck já possui numero maximo de cartas");
		}
		else {

			deckStack.add(novaCarta);
		}

	}

	public Carta tirarCartaTopo(){
		return deckStack.pop();
	}
}
