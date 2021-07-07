package com.unicamp.mc322.trabalho.jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Expansoes {
	private Map<Regiao, Expansao> expansoesMap = new HashMap<Regiao, Expansao>();
	private List<Expansao> expansoesList = new ArrayList<Expansao>();
	
	public void addExpansao(Expansao novaExpansao) {
		if(!expansoesMap.containsKey(novaExpansao.getRegiao())) {
			expansoesMap.put(novaExpansao.getRegiao(), novaExpansao);
			expansoesList.add(novaExpansao);
		}
		else {
			throw new GameException("Região já cadastrada no jogo.");
		}	
	}

	public void addCarta(Regiao regiao, Carta novaCarta) {
		//Add uma nova carta na expansao;
		expansoesMap.get(regiao).addCarta(novaCarta);
	}
	
	public void imprimirNomeExpansoes() {
		// Imprime uma lista das expansoes, com seu nome e sua regiao.
		String info = "";
		for(Expansao expansao : expansoesList) {
			info += expansao.getNome() + "(Regiao: " + expansao.getRegiao() + ")\n";
			
		}
		System.out.println(info);
	}

	public void imprimirCartasExpansoes() {
		for(Expansao expansao : expansoesList) {
			expansao.imprimirCartas();
			System.out.println();
		}
	}

	public Carta getCarta(String nome) {
		//Dado
		return carta;
	}
}


