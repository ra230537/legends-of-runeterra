package com.unicamp.mc322.trabalho;

import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Regiao;

public class Runner {
	
	public static void main(String[] args) {
		Jogo jogo = new Jogo();

		jogo.criarExpansao("Regiao1", Regiao.Bilgewater);
		//Criar expansao;


		jogo.criarExpansao("Regiao1", Regiao.Demacia);

		jogo.criarExpansao("Regiao1", Regiao.Freljord);

		jogo.criarExpansao("Regiao1", Regiao.Ionia);


		//Cria usuarios, tags diferentes;
		jogo.criarUsuario("Roberto");
		jogo.criarUsuario("Cris");

	}
}
