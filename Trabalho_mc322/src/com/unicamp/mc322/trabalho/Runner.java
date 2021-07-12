package com.unicamp.mc322.trabalho;

import com.unicamp.mc322.trabalho.jogador.Deck;
import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Regiao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.*;
import com.unicamp.mc322.trabalho.jogador.Usuario;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.efeitos.*;

public class Runner {
	
	public static void main(String[] args) {
		//para realizar a execução do runner basta retirar o comentário das funções na linhas 244,247 ou 250

		Jogo jogo = new Jogo();

		//Criar expansao;
		//jogo.criarExpansao("Regiao1", Regiao.Bilgewater);

		//Criando expansao Demacia:
		jogo.criarExpansao("Demacia`s Expansion", Regiao.Demacia);

		//Campeao:
		jogo.addCartaNaExpansao(Regiao.Demacia, new Campeao("Garen", 5, 5, 5, null, new LevelUpGaren()));
		//Monstros:
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Tiana", 8, 7, 7, null, new AtacaNexus()));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Vanguarda", 4, 3, 3, null, new BuffTodosAliados(1,1)));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Duelista", 3, 2, 3, null, new ComprarAoMatar()));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Poro", 1, 1, 2, null));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Monstro("Poro Defensor", 1, 2, 1, null, new CartaRestituida()));
		//Feiticos:
		jogo.addCartaNaExpansao(Regiao.Demacia, new Feitico("Julgamento", 8, new GolpearTodos()));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Feitico("Valor Redobrado", 6, new CurarUnidadeCompletamente(), new DobrarStatus()));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Feitico("Golpe Certeiro", 1, new BuffAliadoUnico(1,1)));
		jogo.addCartaNaExpansao(Regiao.Demacia, new Feitico("Combate um-a-um", 2, new SelecionaCombate()));


		//jogo.criarExpansao("Regiao1", Regiao.Freljord);

		//jogo.criarExpansao("Regiao1", Regiao.Ionia);

		//Criando expansao de Noxus:
		jogo.criarExpansao("Noxus` Expansion", Regiao.Noxus);

		//Campeao:
		jogo.addCartaNaExpansao(Regiao.Noxus, new Campeao("Darius", 6, 6, 5, null, new Sobrecarga(), new LevelUpDarius()));
		//Monstros:
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, new Sobrecarga()));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Cavaleiro Basilisco", 4, 3, 4, null, new Sobrecarga(), new BuffAliadoUnico(1,1)));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Saqueador da Legiao", 3, 2, 3, null, new BuffTodosAliados(1,1)));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Balista de Ferro", 3, 3, 4,null, new Sobrecarga()));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Lutador de Arena", 2, 2, 2, null, new BuffAliadoUnico(1,0)));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Monstro("Sabotador da Legiao", 1, 1, 2, null, new GolpeAoNexus(1)));
		//Feiticos:
		jogo.addCartaNaExpansao(Regiao.Noxus, new Feitico("Morte Rodopiante", 3, new SelecionaCombate()));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Feitico("Resolucao Apurada", 3, new BuffAliadoUnico(3,2)));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Feitico("Poderio", 3, new BuffAliadoUnico(3,0)));
		jogo.addCartaNaExpansao(Regiao.Noxus, new Feitico("Punho da Arma", 2, new BuffAliadoUnico(2,0)));

		/*Criando DeckPadrao1*/

		Deck deckPadrao1 = new Deck("Demacia");

		/*Criando os efeitos do deck 1 */
		LevelUpGaren garen1 = new LevelUpGaren();
		LevelUpGaren garen2 = new LevelUpGaren();
		LevelUpGaren garen3 = new LevelUpGaren();
		LevelUpGaren garen4 = new LevelUpGaren();

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

		deckPadrao1.addCarta(new Campeao("Garen", 5, 5, 5, null, garen1));
		deckPadrao1.addCarta(new Campeao("Garen", 5, 5, 5, null, garen2));
		deckPadrao1.addCarta(new Campeao("Garen", 5, 5, 5, null, garen3));
		deckPadrao1.addCarta(new Campeao("Garen", 5, 5, 5, null, garen4));

		//Adicionando monstros no deck
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, Tiana1));
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, Tiana1));
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, Tiana1));
		deckPadrao1.addCarta(new Monstro("Tiana", 8, 7, 7, null, Tiana1));

		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Vanguarda1));
		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Vanguarda1));
		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Vanguarda1));
		deckPadrao1.addCarta(new Monstro("Vanguarda", 4, 3, 3, null, Vanguarda1));

		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Duelista1));
		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Duelista1));
		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Duelista1));
		deckPadrao1.addCarta(new Monstro("Duelista", 3, 2, 3, null, Duelista1));

		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1));
		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1));
		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1));
		deckPadrao1.addCarta(new Monstro("Defensor", 2, 2, 2, Traco.FURIA , 0, 1));

		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null));
		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null));
		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null));
		deckPadrao1.addCarta(new Monstro("Poro", 1, 1, 2, null));

		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, PoroDefensor1));
		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, PoroDefensor1));
		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, PoroDefensor1));
		deckPadrao1.addCarta(new Monstro("Poro Defensor", 1, 2, 1, null, PoroDefensor1));

		//Adicionando feiticos no deck;
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, Julgamento1));
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, Julgamento1));
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, Julgamento1));
		deckPadrao1.addCarta(new Feitico("Julgamento", 8, Julgamento1));

		deckPadrao1.addCarta(new Feitico("Valor Redobrado", 6, ValorRedobrado1, ValorRedobrado2));


		deckPadrao1.addCarta(new Feitico("Golpe Certeiro", 1, GolpeCerteiro1));
		deckPadrao1.addCarta(new Feitico("Golpe Certeiro", 1, GolpeCerteiro1));
		deckPadrao1.addCarta(new Feitico("Golpe Certeiro", 1, GolpeCerteiro1));

		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, CombateUmaUm1));
		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, CombateUmaUm1));
		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, CombateUmaUm1));
		deckPadrao1.addCarta(new Feitico("Combate um-a-um", 2, CombateUmaUm1));

		jogo.AddDeckPadrao(deckPadrao1, Regiao.Demacia);


		/*Criando Deck padrao 2 */
		Deck deckPadrao2 = new Deck("Noxus");

		/*Criando efeitos deck 2 */
		Sobrecarga Darius1 = new Sobrecarga();
		LevelUpDarius Darius2 = new LevelUpDarius();
		Sobrecarga Darius3 = new Sobrecarga();
		LevelUpDarius Darius4 = new LevelUpDarius();
		Sobrecarga Darius5 = new Sobrecarga();
		LevelUpDarius Darius6 = new LevelUpDarius();
		Sobrecarga Darius7 = new Sobrecarga();
		LevelUpDarius Darius8 = new LevelUpDarius();

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
		deckPadrao2.addCarta(new Campeao("Darius", 6, 6, 5, null, Darius1, Darius2));
		deckPadrao2.addCarta(new Campeao("Darius", 6, 6, 5, null, Darius3, Darius4));
		deckPadrao2.addCarta(new Campeao("Darius", 6, 6, 5, null, Darius5, Darius6));
		deckPadrao2.addCarta(new Campeao("Darius", 6, 6, 5, null, Darius7, Darius8));

		//Adicionando monstros no deck 2
		deckPadrao2.addCarta(new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, Cavaleiro1));
		deckPadrao2.addCarta(new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, Cavaleiro1));
		deckPadrao2.addCarta(new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, Cavaleiro1));
		deckPadrao2.addCarta(new Monstro("Cavaleiro Presa Blindada", 6, 5, 6, null, Cavaleiro1));

		deckPadrao2.addCarta(new Monstro("Cavaleiro Basilisco", 4, 3, 4, null, CavaleiroB1, CavaleiroB2));

		deckPadrao2.addCarta(new Monstro("Saqueador da Legiao", 3, 2, 3, null, SaqueadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Saqueador da Legiao", 3, 2, 3, null, SaqueadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Saqueador da Legiao", 3, 2, 3, null, SaqueadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Saqueador da Legiao", 3, 2, 3, null, SaqueadorDaLegiao1));

		deckPadrao2.addCarta(new Monstro("Balista de Ferro", 3, 3, 4,null, BalistadeFerro1));
		deckPadrao2.addCarta(new Monstro("Balista de Ferro", 3, 3, 4,null, BalistadeFerro1));
		deckPadrao2.addCarta(new Monstro("Balista de Ferro", 3, 3, 4,null, BalistadeFerro1));
		deckPadrao2.addCarta(new Monstro("Balista de Ferro", 3, 3, 4,null, BalistadeFerro1));

		deckPadrao2.addCarta(new Monstro("Lutador de Arena", 2, 2, 2, null,LutadorDaArena1));
		deckPadrao2.addCarta(new Monstro("Lutador de Arena", 2, 2, 2, null,LutadorDaArena1));
		deckPadrao2.addCarta(new Monstro("Lutador de Arena", 2, 2, 2, null,LutadorDaArena1));
		deckPadrao2.addCarta(new Monstro("Lutador de Arena", 2, 2, 2, null,LutadorDaArena1));

		deckPadrao2.addCarta(new Monstro("Sabotador da Legiao", 1, 1, 2, null, SabotadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Sabotador da Legiao", 1, 1, 2, null, SabotadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Sabotador da Legiao", 1, 1, 2, null, SabotadorDaLegiao1));
		deckPadrao2.addCarta(new Monstro("Sabotador da Legiao", 1, 1, 2, null, SabotadorDaLegiao1));


		//Adicionando feiticos no deck;

		deckPadrao2.addCarta(new Feitico("Morte Rodopiante", 3, MorteRodopiante1));
		deckPadrao2.addCarta(new Feitico("Morte Rodopiante", 3, MorteRodopiante1));
		deckPadrao2.addCarta(new Feitico("Morte Rodopiante", 3, MorteRodopiante1));
		deckPadrao2.addCarta(new Feitico("Morte Rodopiante", 3, MorteRodopiante1));


		deckPadrao2.addCarta(new Feitico("Resolucao Apurada", 3, ResolucaoApurada1));
		deckPadrao2.addCarta(new Feitico("Resolucao Apurada", 3, ResolucaoApurada1));
		deckPadrao2.addCarta(new Feitico("Resolucao Apurada", 3, ResolucaoApurada1));
		deckPadrao2.addCarta(new Feitico("Resolucao Apurada", 3, ResolucaoApurada1));

		deckPadrao2.addCarta(new Feitico("Poderio", 3, Poderio1));
		deckPadrao2.addCarta(new Feitico("Poderio", 3, Poderio1));
		deckPadrao2.addCarta(new Feitico("Poderio", 3, Poderio1));
		deckPadrao2.addCarta(new Feitico("Poderio", 3, Poderio1));

		deckPadrao2.addCarta(new Feitico("Punho da Arma", 2, PunhodaArma1));
		deckPadrao2.addCarta(new Feitico("Punho da Arma", 2, PunhodaArma1));
		deckPadrao2.addCarta(new Feitico("Punho da Arma", 2, PunhodaArma1));

		jogo.AddDeckPadrao(deckPadrao2, Regiao.Noxus);

		//Cria usuarios, tags diferentes;
		Usuario user1 = jogo.criarUsuario("Roberto");
		Usuario user2 = jogo.criarUsuario("Cris");

		//imprimir lista de expansoes no jogo(nome);
		//jogo.imprimirListaExpansoes();

		//Criar deck:
		//jogo.criarNovoDeck(user1.getId(), "Novo Deck");

		//Editar deck:
		//jogo.editarDeck(user1.getId(), "Novo Deck");

		//Imprime os decks:
		user1.imprimirDecks();

		//Imprime o nome dos decks:
		user1.imprimirDecksNomes();

		//Realizar partida jogador vs jogador!
		//jogo.realizarPartidaJogadorVsJogador(user1.getId(), user2.getId());

		//Realizar partida jogador vs bot!
		//jogo.realizarPartidaJogadorVsComputador(user2.getId());

		//Realiza partida bot vs bot!
		jogo.realizarPartidaComputadorVsComputador();

		jogo.fecharJogo();
	}
}
