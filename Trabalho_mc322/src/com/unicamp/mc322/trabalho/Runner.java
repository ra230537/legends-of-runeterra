package com.unicamp.mc322.trabalho;

import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Regiao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Feitico;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;
import com.unicamp.mc322.trabalho.jogador.Usuario;

public class Runner {
	
	public static void main(String[] args) {
		Jogo jogo = new Jogo();

		//Criar expansao;
		jogo.criarExpansao("Regiao1", Regiao.Bilgewater);
		jogo.addCartaNaExpansao(new Monstro("The Dreadway", 8, 6, 4,<TRACO>,<EFEITO>);

		jogo.addCartaNaExpansao(new Feitico());


		jogo.criarExpansao("Regiao1", Regiao.Demacia);

		jogo.criarExpansao("Regiao1", Regiao.Freljord);

		jogo.criarExpansao("Regiao1", Regiao.Ionia);


		//Cria usuarios, tags diferentes;
		Usuario user1 = jogo.criarUsuario("Roberto");
		Usuario user2 = jogo.criarUsuario("Cris");

	}
}
