package com.unicamp.mc322.trabalho;

import com.unicamp.mc322.trabalho.jogador.Deck;
import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Regiao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Feitico;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;
import com.unicamp.mc322.trabalho.jogador.Usuario;

public class Runner {
	
	public static void main(String[] args) {
		Jogo jogo = new Jogo();

		//Criar expansao;
		jogo.criarExpansao("Regiao1", Regiao.Bilgewater);
		jogo.addCartaNaExpansao(new Monstro("The Dreadway", 8, 6, 4,<TRACO>,<EFEITO>);

		jogo.addCartaNaExpansao(new Feitico("Julgamento", 8, efeito));


		jogo.criarExpansao("Regiao1", Regiao.Demacia);

		jogo.criarExpansao("Regiao1", Regiao.Freljord);

		jogo.criarExpansao("Regiao1", Regiao.Ionia);


		Deck deckPadrao1 = new Deck("Demacia");

		//Adicionando campeao;
		deckPadrao1.addCarta(new Campeao("Garen", 5, 5, 5, null, EFEITO, ));

		//Adicionando monstros no deck;
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, EFEITO));
		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Efeito...));
		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Efeito...));
		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, new Traco() , null));
		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null,null));
		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, Efeito...));

		//Adicionando feiticos no deck;
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, efeito));
		deckPadrao1.addCarta(new Feitico("Valor Redobrado", 6, efeito));
		deckPadrao1.addCarta(new Feitico("Golpe Certeiro", 1, efeito));
		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, efeito));

		jogo.AddDeckPadrao(deckPadrao1, Regiao.Demacia);

		//Cria usuarios, tags diferentes;
		Usuario user1 = jogo.criarUsuario("Roberto");
		Usuario user2 = jogo.criarUsuario("Cris");

	}
}
