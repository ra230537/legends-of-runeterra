package com.unicamp.mc322.trabalho.jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.Regiao;

public class Jogo {
	private Map<String, Expansao> expansoes = new HashMap<String, Expansao>();
	private Map<String, Jogador> jogadores = new HashMap<String, Jogador>(); //Dicionario com jogadores e key = id do jogador
	private List<String> nicksUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usada;
	
	
	public void realizarPartida() {
		boolean partidaAcabou = false; 
		
		do {
		//Ciclo de comandos enquanto a partida está acontecendo;
			
		}while(!partidaAcabou);
		
	}
	
	public void criarUsuario(String nome) {
		Jogador novoJogador = new Jogador(nome);
		do {
		// Faz com que não exista 2 ids iguais cadastrados;
			novoJogador.criarId();
		}while(nicksUtilizados.contains(novoJogador.getId()));
		nicksUtilizados.add(novoJogador.getId());
		jogadores.put(novoJogador.getId(), novoJogador);
		
		//testar (***APAGAR DEPOIS)
		novoJogador.imprimirId();
	}

}
