package com.unicamp.mc322.trabalho.jogo.expansao.carta;

public class Feitico extends Carta {
	public Feitico(String nomeCarta, int custo, Efeito... efeitos) {
		super(nomeCarta, custo, true,efeitos);
	}

	public void imprimirCartaDetalhada() {
		String info = "|<FEITIÇO> NOME: " + this.getNome() + " Custo: " + this.getCusto() + "\n\r";
		info += "|Efeitos: " + "\n\r";
		System.out.println(info);
	}
}
