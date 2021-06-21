package com.unicamp.mc322.trabalho.jogo.expansao;

import java.util.ArrayList;
import java.util.List;

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
	
}
