package com.unicamp.mc322.trabalho;

import com.unicamp.mc322.trabalho.jogador.Deck;
import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Regiao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Feitico;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;
import com.unicamp.mc322.trabalho.jogador.Usuario;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Traco;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos.*;

public class Runner {
	
	public static void main(String[] args) {


		Jogo jogo = new Jogo();

		//Criar expansao;
		jogo.criarExpansao("Regiao1", Regiao.Bilgewater);
		jogo.addCartaNaExpansao(new Monstro("The Dreadway", 8, 6, 4,Traco,Efeito);

		jogo.addCartaNaExpansao(new Feitico("Julgamento", 8, Efeito));


		jogo.criarExpansao("Regiao1", Regiao.Demacia);

		jogo.criarExpansao("Regiao1", Regiao.Freljord);

		jogo.criarExpansao("Regiao1", Regiao.Ionia);

		jogo.criarExpansao("Regiao2", Regiao.Noxus);

		Deck deckPadrao1 = new Deck("Demacia");

		LevelUpGaren garen1 = new LevelUpGaren();
		AtacaNexus Tiana1 = new AtacaNexus();
		BuffTodosAliados Vanguarda1 = new BuffTodosAliados(1,1);
		ComprarAoMatar Duelista1 = new ComprarAoMatar();
		CartaRestituida PoroDefensor1 = new CartaRestituida();
		GolpearTodos Julgamento1 = new GolpearTodos();
		CurarUnidadeCompletamente ValorRedobrado1 = new CurarUnidadeCompletamente();
		DobrarStatus ValorRedobrado2 = new DobrarStatus();
		BuffAliadoUnico GolpeCerteiro1 = new BuffAliadoUnico(1,1);
		SelecionaCombate CombateUmaUm1 = new SelecionaCombate();
		/*Adicionando campeao*/

		deckPadrao1.addCarta(new Monstro("Garen", 5, 5, 5, null, garen1));

		//Adicionando monstros no deck
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, Tiana1));
		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Vanguarda1));
		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Duelista1));
		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1, null));
		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null,null));
		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, PoroDefensor1));

		//Adicionando feiticos no deck;
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, Julgamento1));
		deckPadrao1.addCarta(new Feitico("Valor Redobrado", 6, ValorRedobrado1, ValorRedobrado2));
		deckPadrao1.addCarta(new Feitico("Golpe Certeiro", 1, GolpeCerteiro1));
		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, CombateUmaUm1));

		jogo.AddDeckPadrao(deckPadrao1, Regiao.Demacia);

		/*Deck padrao 2 */
		Deck deckPadrao2 = new Deck("Noxus");

		Sobrecarga Darius1 = new Sobrecarga();
		LevelUpDarius Darius2 = new LevelUpDarius();
		Sobrecarga Cavaleiro1 = new Sobrecarga();
		Sobrecarga CavaleiroB1 = new Sobrecarga();
		BuffAliadoUnico CavaleiroB2 = new BuffAliadoUnico(1,1);
		SelecionaCombate MorteRodopiante1 = new SelecionaCombate();
		BuffAliadoUnico ResolucaoApurada1 = new BuffAliadoUnico(3,2);
		BuffAliadoUnico Poderio1 = new BuffAliadoUnico(3,0);
		BuffTodosAliados SaqueadorDaLegiao1 = new BuffTodosAliados(1,1);
		Sobrecarga BalistadeFerro1 = new Sobrecarga();
		BuffAliadoUnico PunhodaArma1 = new BuffAliadoUnico(2,0);
		BuffAliadoUnico LutadorDaArena1 = new BuffAliadoUnico(1,0);
		GolpeAoNexus SabotadorDaLegiao1 = new GolpeAoNexus(1);
		/*Adicionando campeao*/
		deckPadrao1.addCarta(new Monstro("Darius", 6, 6, 5, null, Darius1, Darius2));

		//Adicionando monstros no deck
		deckPadrao1.addCarta(new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, Cavaleiro1));
		deckPadrao1.addCarta(new Monstro("Cavaleiro Basilisco", 4, 3, 4, null, CavaleiroB1, CavaleiroB2));
		deckPadrao1.addCarta(new Monstro("Saqueador da Legiao", 3, 2, 3, null, SaqueadorDaLegiao1));
		deckPadrao1.addCarta(new Monstro("Balista de Ferro", 3, 3, 4,null, BalistadeFerro1));
		deckPadrao1.addCarta(new Monstro("Lutador de Arena", 2, 2, 2, null,LutadorDaArena1));
		deckPadrao1.addCarta(new Monstro("Sabotador da Legiao", 1, 1, 2, null, SabotadorDaLegiao1));

		//Adicionando feiticos no deck;
		deckPadrao1.addCarta(new Feitico("Morte Rodopiante", 3, MorteRodopiante1));
		deckPadrao1.addCarta(new Feitico("Resolucao Apurada", 3, ResolucaoApurada1));
		deckPadrao1.addCarta(new Feitico("Poderio", 3, Poderio1));
		deckPadrao1.addCarta(new Feitico("Punho da Arma", 2, PunhodaArma1));


		jogo.AddDeckPadrao(deckPadrao2, Regiao.Noxus);

		//Cria usuarios, tags diferentes;
		Usuario user1 = jogo.criarUsuario("Roberto");
		Usuario user2 = jogo.criarUsuario("Cris");

	}
}
