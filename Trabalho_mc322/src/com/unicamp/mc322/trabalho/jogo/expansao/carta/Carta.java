package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;
import java.util.Collections;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.Regiao;

public class Carta {
	private boolean temBarreira;
	private boolean tipo; //False = monstro, true = feitiço
	private String nome;
	private int custo;
	private Regiao regiao;
	private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); //uma carta pode ter mais de um Efeito
	
	protected Carta(String nome, int custo, boolean tipo, Efeito... efeitos){
		this.tipo = tipo;
        this.nome = nome;
        this.custo = custo;
        Collections.addAll(listaEfeitos,efeitos);
	}


	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void imprimirCarta() {
		//imprime carta
	}



	/**
	 *
	 * @param efeito
	 * recebe um dos efeitos que a carta possui e que foi permitido ser usado pelo board manager
	 * manda a mensagem para a classe efeito de que ela pode usa-lo.
	 */
	public void ativarEfeito(Efeito efeito, Mesa mesa){
		efeito.usarEfeito(mesa);
	}

	public ArrayList<Efeito> getListaEfeitos() {
		return listaEfeitos;
	}

	public int getCusto() {
		return custo;
	}

	public String getNome() {
		return nome;
	}

	public boolean getTipo(){
		return tipo;
	}

}
