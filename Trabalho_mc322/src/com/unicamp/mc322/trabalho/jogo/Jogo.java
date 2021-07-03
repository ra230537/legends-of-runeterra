package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.unicamp.mc322.trabalho.jogador.Deck;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.Regiao;

public class Jogo {
	private Scanner comandos = new Scanner(System.in);
	private Expansoes expansoes = new Expansoes();
	private Map<String, Jogador> jogadores = new HashMap<String, Jogador>(); //Dicionario com jogadores e key = id do jogador
	private List<String> nicksUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usada;
	
	public void realizarPartida() {
		boolean partidaAcabou = false; 
		
		do {
		//Ciclo de comandos enquanto a partida está acontecendo;
			
		}while(!partidaAcabou);
		
	}
	
	public void criarUsuario(String nome) {
		Jogador novoJogador = new Jogador(nome, this);
		do {
		// Faz com que não exista 2 ids iguais cadastrados;
			novoJogador.criarId();
		}while(nicksUtilizados.contains(novoJogador.getId()));
		nicksUtilizados.add(novoJogador.getId());
		jogadores.put(novoJogador.getId(), novoJogador);
		System.out.printf("Novo jogador cadastrado com o id: %s\n", novoJogador.getId());
		
	}
	
	public void criarNovoDeck(String idJogador, String nomeDeck) {
		//Cria um novo deck de cartas e é dado um nome para ele;
		Jogador jogador = jogadores.get(idJogador);
		Deck novoDeck = new Deck(nomeDeck);
		System.out.println("Escolha as regioes do seu deck, no maximo 2 regiões:\n");
		expansoes.imprimirExpansoes();
		System.out.println("\n2 Regiões restantes.");
		//...
		//...
		jogador.addNovoDeck(nomeDeck, novoDeck);
	}
	
	public Expansoes getExpansoes() {
		return expansoes;
	}
	
	

}
