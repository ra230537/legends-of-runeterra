package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;

public class Feitico extends Carta {
	public Feitico(String nomeCarta, int custo, Efeito... efeitos) {
		super(nomeCarta, custo, true,efeitos);
	}

	public Feitico(String nomeCarta, int custo, ArrayList<Efeito> efeitos) {
		super(nomeCarta, custo, true,efeitos);
	}

	@Override
	public void imprimirCartaDetalhada() {
		String info = "|<FEITIÇO> NOME: " + this.getNome() + " Custo: " + this.getCusto() + "\n\r";
		info += "|" + this.getTextoEfeitos();
		System.out.println(info);
	}

	public void imprimirCarta(){
		System.out.print("  |"+ this.getNome() + "(" + this.getCusto() + ")" + "|");
	}

	@Override
	public void imprimirNome() {
		System.out.print("|<FEITIÇO> NOME: " + this.getNome()+ "|");
	}

}
