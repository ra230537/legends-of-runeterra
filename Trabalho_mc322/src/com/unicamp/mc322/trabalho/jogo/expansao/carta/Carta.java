package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;
import java.util.Collections;

import com.unicamp.mc322.trabalho.jogo.expansao.Regiao;

public class Carta {
	private String nome;
	private int custo;
	private Regiao regiao;
	private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); //uma carta pode ter mais de um feitiço
	
	protected Carta(String nome, int custo, Efeito... efeitos){
        this.nome = nome;
        this.custo = custo;
        Collections.addAll(listaEfeitos,efeitos);
	}
	
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	
	public void imprimirCarta() {
	//Imprime a carta
	}

}
