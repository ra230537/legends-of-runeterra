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
	
	
	public void realizarPartida() {
		boolean partidaAcabou = false; 
		
		do {
		//Ciclo de comandos enquanto a partida está acontecendo;
			
		}while(!partidaAcabou);
		
	}

}
