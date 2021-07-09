package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.Regiao;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map;

public class Deck {
	private String nome;
	private Estado estado = Estado.Inutilizavel;
	private Stack <Carta> deckStack = new Stack<Carta>()
	private Map<String, Carta> deckMap = new HashMap<String, Carta>(); //Permite encontrar carta pelo nome com mais facilidade
	
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
		if(deckMap.containsKey(nomeCarta)) {
			deckStack.remove(deckMap.get(nomeCarta));
			deckMap.remove(nomeCarta);
		}
		else {
			throw new GameException("O deck não contem uma carta com esse nome");
		}
	}

	public void addCarta(Carta novaCarta) {
		if(deckStack.size() == 40) {
			throw new GameException("Deck já possui numero maximo de cartas");
		}
		else {
			deckMap.put(novaCarta.getNome(), novaCarta);
			deckStack.add(novaCarta);
		}
	}

	public void setarRegiaoCartas(Regiao regiao) {
		for(Carta carta : deckStack) {
			carta.setRegiao(regiao);
		}
	}

	public Carta tirarCartaTopo(){
		return deckStack.pop();
	}
}
