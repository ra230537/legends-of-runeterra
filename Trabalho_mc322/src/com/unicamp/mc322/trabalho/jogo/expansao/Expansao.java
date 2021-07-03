package com.unicamp.mc322.trabalho.jogo.expansao;

import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Expansao {
	private String nome;
	private Regiao regiao;
	private List<Carta> cartas = new ArrayList<Carta>();

	
	
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
	
	public void criarCarta(Carta novaCarta) {
	//cria uma nova carta que será linkada a expansão
		if(!cartas.contains(novaCarta)) {
			novaCarta.setRegiao(regiao);
			cartas.add(novaCarta);
		}
		else {
		//Exception, carta ja na expansao;
			throw new GameException("Esta carta já está na expansão.");
		}
		
	}
	
	public void imprimirCartas() {
	//Imprime cartas presentes na expansão;
		for(int i = 0; i < cartas.size(); i++) {
			cartas.get(i).imprimirCarta();
			if((i+1) % 3 == 0) {
				System.out.println();
			}
		}
	}
	
}
