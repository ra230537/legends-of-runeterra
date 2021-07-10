package com.unicamp.mc322.trabalho.jogo.expansao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.Regiao;

public class Expansao {
	private String nome;
	private Regiao regiao;
	private List<Carta> cartas = new ArrayList<Carta>();
	private Map<String, Carta> cartasMap = new HashMap<String, Carta>();
	//como inserir as cartas na região?
	//porque um nome?

	public Expansao(String nome, Regiao regiao) {
		this.nome = nome;
		this.regiao = regiao;
	}

	public String getNome() {
		return nome;
	}
	
	public Regiao getRegiao() {
		return regiao;
	}
	
	public void addCarta(Carta novaCarta) {
	//add uma nova carta que será linkada a expansão
		if(!cartas.contains(novaCarta)) {
			novaCarta.setRegiao(regiao);
			cartas.add(novaCarta);
			cartasMap.put(novaCarta.getNome(), novaCarta);
		}
		else {
		//Exception, carta ja na expansao;
			throw new GameException("Esta carta já está na expansão.");
		}
	}

	public Carta getCartaPeloNome(String nome) {
		return cartasMap.get(nome);
	}
	
	public void imprimirCartas() {
	//Imprime cartas presentes na expansão;
		System.out.println(nome + "(Regiao: " + regiao + "):");
		for(int i = 0; i < cartas.size(); i++) {
			cartas.get(i).imprimirCarta();
			if((i+1) % 3 == 0) {
				System.out.println();
			}
		}
	}
	
}
