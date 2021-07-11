package com.unicamp.mc322.trabalho.jogador;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.Regiao;

import java.util.*;

public class Deck {
	private String nome;
	private EstadoDeck estadoDeck = EstadoDeck.Inutilizavel;
	private ArrayList<Regiao> regioes = new ArrayList<Regiao>(); //Regioes que o deck possui(max2)
	private Stack <Carta> deckStack = new Stack<Carta>();
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

	public EstadoDeck getEstado() {
		return estadoDeck;
	}

	public Stack<Carta> getDeckStack() {
		return deckStack;
	}

	public void imprimirQuantidade() {
		System.out.printf("%d/40", deckStack.size());
	}

	public void imprimirDeck() {
		System.out.println("Nome do deck: " + nome + "Estado: " + estadoDeck);
		for(int i = 0; i < deckStack.size(); i++) {
			deckStack.get(i).imprimirNome();
			System.out.print("  ");
			if((i+1) % 3 == 0) {
				System.out.println();
			}
		}
	}

	public void removerCarta(String nomeCarta) {
		if(deckMap.containsKey(nomeCarta)) {
			if(deckStack.size() == 40) {
				estadoDeck = EstadoDeck.Inutilizavel;
			}
			Carta carta = deckMap.get(nomeCarta);
			if (ehUltimaCartaDaRegiao(carta)) {
				regioes.remove(carta.getRegiao());
			}

			deckStack.remove(carta);
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
			if(deckStack.size() == 40) {
				estadoDeck = EstadoDeck.Utilizavel;
			}
			if(!regioes.contains(novaCarta.getRegiao())) {
				regioes.add(novaCarta.getRegiao());
			}
		}
	}

	private boolean ehUltimaCartaDaRegiao(Carta ultimaCarta) {
		//verifica se essa é a ultima carta da regiao no deck;
		for(Carta carta : deckStack) {
			if(!carta.equals(ultimaCarta) && carta.getRegiao().equals(ultimaCarta.getRegiao())) {
				return false;
			}
		}
		return true;
	}

	public void setarRegiaoCartas(Regiao regiao) {
		regioes.add(regiao);
		for(Carta carta : deckStack) {
			carta.setRegiao(regiao);
		}
	}

	public ArrayList<Regiao> getRegioes() {
		return regioes;
	}

	public Carta tirarCartaTopo(){
		return deckStack.pop();
	}

	public void embaralharCartas(){
		Collections.shuffle(deckStack);
	}
}
