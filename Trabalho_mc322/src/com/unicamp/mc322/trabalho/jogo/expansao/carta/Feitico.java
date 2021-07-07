package com.unicamp.mc322.trabalho.jogo.expansao.carta;

public class Feitico extends Carta {
	public Feitico(String nomeCarta, int custo, Efeito... efeitos) {
		super(nomeCarta, custo, efeitos);
	}

	@Override
	public void imprimirCarta() {
		System.out.printf("|<FEITIÇO> Nome: %s custo: %d efeitos:  |", this.getNome(), this.getCusto());
	}
}
