package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;
import java.util.Collections;

import com.unicamp.mc322.trabalho.jogo.expansao.Regiao;

public class Carta {
	private String nome;
	private int custo;
	private Regiao regiao;
	private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); //uma carta pode ter mais de um Efeito
	
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

	public ArrayList <Efeito> mostrarEfeitos(){
		return listaEfeitos;
	}

	/**
	 *
	 * @param efeito
	 * recebe um dos efeitos que a carta possui e que foi permitido ser usado pelo board manager
	 * manda a mensagem para a classe efeito de que ela pode usa-lo.
	 */
	public void ativarEfeito(Efeito efeito){
		efeito.usarEfeito();
	}

}
